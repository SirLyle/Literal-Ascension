package com.jamieswhiteshirt.literalascension.core.patcher

import com.jamieswhiteshirt.literalascension.api.ISpecialLadderBlock
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class LiteralAscensionHooks {
    companion object {
        private val ladderShim = object : ISpecialLadderBlock {
            override fun canClimb(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
                if (state.block.isLadder(state, world, pos, entity)) {
                    return isIntersectingDefault(pos, entity)
                } else {
                    return false
                }
            }
        }

        @JvmStatic
        fun isLivingOnLadder(@Suppress("UNUSED_PARAMETER") insideState: IBlockState, world: World, @Suppress("UNUSED_PARAMETER") pos: BlockPos, entity: EntityLivingBase): Boolean {
            if (entity !is EntityPlayer || !entity.isSpectator) {
                val bb = entity.entityBoundingBox
                val minX = MathHelper.floor(bb.minX) - 1
                val maxX = MathHelper.ceil(bb.maxX) + 1
                val minY = MathHelper.floor(bb.minY) - 1
                val maxY = MathHelper.ceil(bb.maxY) + 1
                val minZ = MathHelper.floor(bb.minZ) - 1
                val maxZ = MathHelper.ceil(bb.maxZ) + 1
                for (x in minX..maxX) {
                    for (y in minY..maxY) {
                        for (z in minZ..maxZ) {
                            val ladderPos = BlockPos(x, y, z)
                            val state = world.getBlockState(ladderPos)
                            val block = state.block
                            val ladderImpl = when (block) {
                                is ISpecialLadderBlock -> block
                                else -> ladderShim
                            }
                            if (ladderImpl.canClimb(state, world, ladderPos, entity)) {
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
