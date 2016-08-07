package com.jamieswhiteshirt.literalascension.common.block

import com.jamieswhiteshirt.literalascension.api.ICarvableBlock
import com.jamieswhiteshirt.literalascension.api.ILadderBlock
import com.jamieswhiteshirt.literalascension.common.EnumCarvedBlockType
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.Item
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*

class BlockNotched(type: EnumCarvedBlockType) : BlockCarvedBase(type), ICarvableBlock, ILadderBlock {
    companion object {
        val SOUTH: PropertyBool = PropertyBool.create("south")
        val WEST: PropertyBool = PropertyBool.create("west")
        val NORTH: PropertyBool = PropertyBool.create("north")
        val EAST: PropertyBool = PropertyBool.create("east")

        var PROPERTIES = arrayOf(SOUTH, WEST, NORTH, EAST)
    }

    init {
        defaultState = PROPERTIES.fold(blockState.baseState, { state, property ->
            state.withProperty(property, false)
        })
    }

    override fun carveSide(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, toolLevel: Int): Boolean {
        if (toolLevel >= type.material.toolLevel) {
            if (facing.axis != EnumFacing.Axis.Y) {
                val property = PROPERTIES[facing.horizontalIndex]
                if (!state.getValue(property)) {
                    if (!world.isRemote) {
                        world.setBlockState(pos, state.withProperty(property, true))
                    }
                    return true
                }
            }
            else {
                if (!world.isRemote) {
                    world.setBlockState(pos, type.chuteBlock().defaultState)
                }
                return true
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

    override fun isLadder(state: IBlockState, world: IBlockAccess, pos: BlockPos, entity: EntityLivingBase): Boolean {
        for (i in PROPERTIES.indices) {
            if (state.getValue(PROPERTIES[i])) {
                if (isLadderIntersectingDefault(pos.offset(EnumFacing.getHorizontal(i)), entity)) {
                    return true
                }
            }
        }
        return false
    }
}