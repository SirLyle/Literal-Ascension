package com.jamieswhiteshirt.literalascension.api

import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface ICarvableBlock {
    fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean

    fun getCarveMaterial(state: IBlockState, world: World, pos: BlockPos): ICarveMaterial
}
