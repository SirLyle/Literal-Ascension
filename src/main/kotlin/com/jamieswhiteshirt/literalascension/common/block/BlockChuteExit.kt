package com.jamieswhiteshirt.literalascension.common.block

import net.minecraft.block.BlockHorizontal
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockChuteExit(material: Material) : BlockChuteBase(material) {
    companion object {
        val FACING: PropertyEnum<EnumFacing> = BlockHorizontal.FACING
    }

    init {
        defaultState = blockState.baseState.withProperty(FACING, EnumFacing.NORTH)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.getHorizontal(meta))
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).horizontalIndex
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    override fun onBlockPlaced(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState {
        return defaultState.withProperty(FACING, placer.horizontalFacing.opposite)
    }

    override fun onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        world.setBlockState(pos, defaultState.withProperty(FACING, placer.horizontalFacing.opposite))
    }

    override fun isSideSolid(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing): Boolean {
        return side.axis != EnumFacing.Axis.Y && state.getActualState(world, pos).getValue(FACING) != side
    }
}