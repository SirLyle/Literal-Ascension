package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

class StepladderOldWood(type: BlockPlanks.EnumType, parent: Stepladders) : StepladderWoodBase(type, Blocks.LOG, type.metadata, parent)
