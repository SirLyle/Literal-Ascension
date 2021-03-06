package com.jamieswhiteshirt.literalascension.api

import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface ICarvingBehaviour {
    /**
     * Try carving this block on a specific facing and return true if it succeeded.
     */
    fun tryCarve(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean

    /**
     * The carving material of this block.
     */
    val carvingMaterial: ICarvingMaterial
}
