package com.jamieswhiteshirt.literalascension.common.stepladder

import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

class StepladderNewWood(type: BlockPlanks.EnumType) : StepladderWoodBase(type, Blocks.LOG2, type.metadata - 4)
