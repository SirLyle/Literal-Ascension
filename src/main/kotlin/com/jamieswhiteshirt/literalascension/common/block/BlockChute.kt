package com.jamieswhiteshirt.literalascension.common.block

import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class BlockChute(material: Material) : BlockChuteBase(material) {
    override fun isSideSolid(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing): Boolean {
        return side.axis != EnumFacing.Axis.Y
    }
}