package com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.carvedblocks

import com.jamieswhiteshirt.literalascension.common.features.carving.carvingmaterials.CarvingMaterialNewWood
import net.minecraft.block.BlockLog
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockPlanks
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CarvedBlockNewWood(type: BlockPlanks.EnumType, parent: CarvingMaterialNewWood) : CarvedBlock(
        Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, type),
        "log_${type.getName()}",
        "log_${type.unlocalizedName}",
        parent
) {
    override fun tryCarveModelBlock(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing): Boolean {
        if (state.getValue(BlockNewLog.LOG_AXIS) == BlockLog.EnumAxis.Y) {
            return super.tryCarveModelBlock(state, world, pos, facing)
        }
        else {
            return false
        }
    }
}
