package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomainMinecraft
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

class StepladderNewWood(type: BlockPlanks.EnumType, parent: StepladderDomainMinecraft) : StepladderWood(
        Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, type),
        Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, type),
        type.getName(),
        parent
)
