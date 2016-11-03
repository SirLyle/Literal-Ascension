package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraft.block.BlockOldLog
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

class StepladderOldWood(type: BlockPlanks.EnumType, parent: Stepladders) : StepladderWoodBase(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, type), type, parent)
