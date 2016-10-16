package com.jamieswhiteshirt.literalascension.common.carvedblock

import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import net.minecraft.block.state.IBlockState

class CarvedBlock(
        val modelState: IBlockState,
        val material: CarvedBlockMaterial,
        val name: String
) {
    val chute = BlockChute(this)
    val notched = BlockNotched(this)
}
