package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.StepladderDomainMinecraft
import net.minecraft.block.BlockOldLog
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

class StepladderOldWood(type: BlockPlanks.EnumType, parent: StepladderDomainMinecraft) : StepladderWood(
        Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, type),
        Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, type),
        type.getName(),
        parent
)