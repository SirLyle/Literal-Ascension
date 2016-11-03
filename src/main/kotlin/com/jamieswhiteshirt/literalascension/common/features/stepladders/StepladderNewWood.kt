package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

class StepladderNewWood(type: BlockPlanks.EnumType, parent: Stepladders) : StepladderWoodBase(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, type), type, parent)
