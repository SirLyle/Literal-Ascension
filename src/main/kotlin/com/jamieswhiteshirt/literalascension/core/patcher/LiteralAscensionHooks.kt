package com.jamieswhiteshirt.literalascension.core.patcher

import com.jamieswhiteshirt.literalascension.api.ISpecialLadderBlock
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockAccess

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
        fun isLivingOnLadder(entity: EntityLivingBase): Boolean {
            val world = entity.worldObj
            world.theProfiler.startSection("isLivingOnLadder")
            if (entity !is EntityPlayer || !entity.isSpectator) {
                val bb = entity.entityBoundingBox
                val minX = MathHelper.floor_double(bb.minX) - 1
                val maxX = MathHelper.ceiling_double_int(bb.maxX)
                val minY = MathHelper.floor_double(bb.minY) - 1
                val maxY = MathHelper.ceiling_double_int(bb.maxY)
                val minZ = MathHelper.floor_double(bb.minZ) - 1
                val maxZ = MathHelper.ceiling_double_int(bb.maxZ)
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
                                world.theProfiler.endSection()
                                return true
                            }
                        }
                    }
                }
            }

            world.theProfiler.endSection()
            return false
        }
    }
}
