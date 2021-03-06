package com.jamieswhiteshirt.literalascension.api

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.ForgeModContainer

/**
 * Equivalent to ordinary ladders with Forge's fullBoundingBoxLadders configuration enabled.
 */
fun isIntersectingForgeFullBoundingBox(pos: BlockPos, entity: EntityLivingBase): Boolean {
    return AxisAlignedBB(pos, pos.add(1, 1, 1)).intersects(entity.entityBoundingBox)
}

/**
 * Equivalent to ordinary ladders with Forge's fullBoundingBoxLadders configuration disabled.
 */
fun isIntersectingVanilla(pos: BlockPos, entity: EntityLivingBase): Boolean {
    if (pos.x == MathHelper.floor(entity.posX)) {
        if (pos.y == MathHelper.floor(entity.entityBoundingBox.minY)) {
            if (pos.z == MathHelper.floor(entity.posZ)) {
                return true
            }
        }
    }
    return false
}

interface ISpecialLadderBlock {
    /**
     * Return true if the player can climb this block.
     */
    fun canClimb(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean

    /**
     * Equivalent to ordinary ladders with Forge's current fullBoundingBoxLadders configuration.
     */
    fun isIntersectingDefault(pos: BlockPos, entity: EntityLivingBase): Boolean {
        if (ForgeModContainer.fullBoundingBoxLadders) {
            return isIntersectingForgeFullBoundingBox(pos, entity)
        } else {
            return isIntersectingVanilla(pos, entity)
        }
    }
}
