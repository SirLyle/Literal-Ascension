package com.jamieswhiteshirt.literalascension.common.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

abstract class BlockChuteBase(material: Material) : Block(material) {
    companion object {
        private val collisionBoxesAny = arrayOf(
                AxisAlignedBB(0.0,  0.0 / 16.0, 0.0, 1.0,  2.0 / 16.0, 1.0),
                AxisAlignedBB(0.0, 14.0 / 16.0, 0.0, 1.0, 16.0 / 16.0, 1.0),
                AxisAlignedBB(0.0, 0.0,  0.0 / 16.0, 1.0, 1.0,  2.0 / 16.0),
                AxisAlignedBB(0.0, 0.0, 14.0 / 16.0, 1.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB( 0.0 / 16.0, 0.0, 0.0,  2.0 / 16.0, 1.0, 1.0),
                AxisAlignedBB(14.0 / 16.0, 0.0, 0.0, 16.0 / 16.0, 1.0, 1.0)
        )
    }

    override fun addCollisionBoxToList(state: IBlockState, world: World, pos: BlockPos, entityBox: AxisAlignedBB, collisionBoxes: MutableList<AxisAlignedBB>, entity: Entity?) {
        for (collisionBox in getCollisionBoxList(state, world, pos)) {
            Block.addCollisionBoxToList(pos, entityBox, collisionBoxes, collisionBox)
        }
    }

    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

    override fun isLadder(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        return true
    }

    override fun collisionRayTrace(state: IBlockState, world: World, pos: BlockPos, start: Vec3d, end: Vec3d): RayTraceResult?
    {
        var bestResult: RayTraceResult? = null
        var d1 = 0.0

        getCollisionBoxList(state.getActualState(world, pos), world, pos).map {
            rayTrace(pos, start, end, it)
        }.filterNotNull().forEach {
            val d0 = it.hitVec.squareDistanceTo(end)
            if (d0 > d1) {
                bestResult = it
                d1 = d0
            }
        }

        return bestResult
    }

    fun getCollisionBoxList(state: IBlockState, world: World, pos: BlockPos): List<AxisAlignedBB> {
        return EnumFacing.VALUES.filter {
            isSideSolid(state, world, pos, it)
        }.map {
            collisionBoxesAny[it.index]
        }
    }
}
