package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.StepladderDomainMinecraft
import net.minecraft.block.BlockOldLog
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

class StepladderOldWood(type: BlockPlanks.EnumType, parent: StepladderDomainMinecraft) : StepladderWoodBase(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, type), type, parent)
