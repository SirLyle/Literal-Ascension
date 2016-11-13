package com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.carvableblocktypes

import com.jamieswhiteshirt.literalascension.features.carving.carvingdomains.carvableblocks.CarvableBlockOldWood
import net.minecraft.block.BlockLog
import net.minecraft.block.BlockOldLog
import net.minecraft.block.BlockPlanks
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CarvableBlockTypeOldWood(type: BlockPlanks.EnumType, parent: CarvableBlockOldWood) : CarvableBlockType(
        Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, type),
        type.getName(),
        parent
) {
    override fun tryCarveModelBlock(state: IBlockState, world: World, pos: BlockPos, facing: EnumFacing): Boolean {
        if (state.getValue(BlockOldLog.LOG_AXIS) == BlockLog.EnumAxis.Y) {
            return super.tryCarveModelBlock(state, world, pos, facing)
        }
        else {
            return false
        }
    }
}
