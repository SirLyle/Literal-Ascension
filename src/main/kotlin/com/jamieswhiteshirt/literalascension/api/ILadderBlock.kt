package com.jamieswhiteshirt.literalascension.api

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.ForgeModContainer

interface ILadderBlock {
    fun isLadder(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean

    fun isLadderIntersectingForgeFullBoundingBox(pos: BlockPos, entity: EntityLivingBase): Boolean {
        return AxisAlignedBB(pos, pos.add(1, 1, 1)).intersectsWith(entity.entityBoundingBox)
    }

    fun isLadderIntersectingVanilla(pos: BlockPos, entity: EntityLivingBase): Boolean {
        if (pos.x == MathHelper.floor_double(entity.posX)) {
            if (pos.y == MathHelper.floor_double(entity.entityBoundingBox.minY)) {
                if (pos.z == MathHelper.floor_double(entity.posZ)) {
                    return true
                }
            }
        }
        return false
    }

    fun isLadderIntersectingDefault(pos: BlockPos, entity: EntityLivingBase): Boolean {
        if (ForgeModContainer.fullBoundingBoxLadders) {
            return isLadderIntersectingForgeFullBoundingBox(pos, entity)
        } else {
            return isLadderIntersectingVanilla(pos, entity)
        }
    }
}
