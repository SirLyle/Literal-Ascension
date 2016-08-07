package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ICarvableBlock
import com.jamieswhiteshirt.literalascension.common.EnumCarvedBlockType
import net.minecraft.block.Block
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class BlockChute(type: EnumCarvedBlockType) : BlockCarvedBase(type), ICarvableBlock {
    companion object {
        private val collisionBoxesSides = arrayOf(
                AxisAlignedBB(0.0, 0.0, 14.0 / 16.0, 1.0, 1.0, 16.0 / 16.0),
                AxisAlignedBB(0.0 / 16.0, 0.0, 0.0, 2.0 / 16.0, 1.0, 1.0),
                AxisAlignedBB(0.0, 0.0, 0.0 / 16.0, 1.0, 1.0, 2.0 / 16.0),
                AxisAlignedBB(14.0 / 16.0, 0.0, 0.0, 16.0 / 16.0, 1.0, 1.0)
        )

        val SOUTH: PropertyBool = PropertyBool.create("south")
        val WEST: PropertyBool = PropertyBool.create("west")
        val NORTH: PropertyBool = PropertyBool.create("north")
        val EAST: PropertyBool = PropertyBool.create("east")

        var PROPERTIES = arrayOf(SOUTH, WEST, NORTH, EAST)
    }

    init {
        defaultState = PROPERTIES.fold(blockState.baseState, { state, property ->
            state.withProperty(property, true)
        })
    }

    override fun carveSide(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, toolLevel: Int): Boolean {
        if (toolLevel >= type.material.toolLevel) {
            if (facing.axis != EnumFacing.Axis.Y) {
                val property = PROPERTIES[facing.horizontalIndex]
                if (state.getValue(property)) {
                    val newState = state.withProperty(property, false)
                    if (PROPERTIES.any { newState.getValue(it) }) {
                        if (!world.isRemote) {
                            world.setBlockState(pos, newState)
                        }
                        return true
                    }
                }
            }
        }

        return false
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        var state = defaultState
        for (i in PROPERTIES.indices) {
            state.withProperty(PROPERTIES[i], ((meta shr i) and 1) == 1)
        }
        return state
    }

    override fun getMetaFromState(state: IBlockState): Int {
        var meta = 0
        for (i in PROPERTIES.indices) {
            if (state.getValue(PROPERTIES[i])) {
                meta = meta or (1 shl i)
            }
        }
        return meta
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, *PROPERTIES)
    }

    override fun addCollisionBoxToList(state: IBlockState, world: World, pos: BlockPos, entityBox: AxisAlignedBB, collisionBoxes: MutableList<AxisAlignedBB>, entity: Entity?) {
        for (collisionBox in getCollisionBoxList(state, world, pos)) {
            Block.addCollisionBoxToList(pos, entityBox, collisionBoxes, collisionBox)
        }
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        val list = getCollisionBoxList(state, source, pos)
        if (list.size > 0) {
            return list.reduce({ a, b ->
                a.union(b)
            })
        }
        else {
            return super.getBoundingBox(state, source, pos)
        }
    }

    override fun isOpaqueCube(state: IBlockState?): Boolean {
        return false
    }

    override fun isFullCube(state: IBlockState?): Boolean {
        return false
    }

    override fun isSideSolid(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing): Boolean {
        return side.axis != EnumFacing.Axis.Y && state.getActualState(world, pos).getValue(PROPERTIES[side.horizontalIndex])
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

    override fun isLadder(state: IBlockState?, world: IBlockAccess?, pos: BlockPos?, entity: EntityLivingBase?): Boolean {
        return true
    }

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item? {
        return modelBlock.getItemDropped(type.modelState, rand, fortune)
    }

    override fun damageDropped(state: IBlockState): Int {
        return modelBlock.damageDropped(type.modelState)
    }

    fun getCollisionBoxList(state: IBlockState, source: IBlockAccess, pos: BlockPos): List<AxisAlignedBB> {
        return EnumFacing.VALUES.filter {
            isSideSolid(state, source, pos, it)
        }.map {
            collisionBoxesSides[it.horizontalIndex]
        }
    }
}
