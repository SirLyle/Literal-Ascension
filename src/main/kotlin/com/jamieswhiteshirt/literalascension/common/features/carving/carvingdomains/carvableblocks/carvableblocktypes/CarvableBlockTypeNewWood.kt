package com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.carvableblocktypes

import com.jamieswhiteshirt.literalascension.common.features.carving.carvingdomains.carvableblocks.CarvableBlockNewWood
import net.minecraft.block.BlockLog
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockPlanks
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CarvableBlockTypeNewWood(type: BlockPlanks.EnumType, parent: CarvableBlockNewWood) : CarvableBlockType(
        Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, type),
        type.getName(),
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
