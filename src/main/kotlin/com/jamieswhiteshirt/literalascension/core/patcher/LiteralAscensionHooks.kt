package com.jamieswhiteshirt.literalascension.core.patcher

import com.jamieswhiteshirt.literalascension.api.ILadderBlock
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class LiteralAscensionHooks {
    companion object {
        private val ladderShim = object : ILadderBlock {
            override fun isLadder(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
                if (state.block.isLadder(state, world, pos, entity)) {
                    return isLadderIntersectingDefault(pos, entity)
                } else {
                    return false
                }
            }
        }

        @JvmStatic
        fun isLivingOnLadder(insideState: IBlockState?, world: World, pos: BlockPos, entity: EntityLivingBase): Boolean {
            if (entity !is EntityPlayer || !entity.isSpectator) {
                val bb = entity.entityBoundingBox
                val minX = MathHelper.floor_double(bb.minX) - 1
                val maxX = MathHelper.ceiling_double_int(bb.maxX) + 1
                val minY = MathHelper.floor_double(bb.minY) - 1
                val maxY = MathHelper.ceiling_double_int(bb.maxY) + 1
                val minZ = MathHelper.floor_double(bb.minZ) - 1
                val maxZ = MathHelper.ceiling_double_int(bb.maxZ) + 1
                for (x in minX..maxX) {
                    for (y in minY..maxY) {
                        for (z in minZ..maxZ) {
                            val ladderPos = BlockPos(x, y, z)
                            val state = world.getBlockState(ladderPos)
                            val block = state.block
                            val ladderImpl = when (block) {
                                is ILadderBlock -> block
                                else -> ladderShim
                            }
                            if (ladderImpl.isLadder(state, world, ladderPos, entity))
                            {
                                return true
                            }
                        }
                    }
                }
            }

            return false
        }
    }
}
