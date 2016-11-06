package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.StepladderDomainMinecraft
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks

class StepladderNewWood(type: BlockPlanks.EnumType, parent: StepladderDomainMinecraft) : StepladderWoodBase(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, type), type, parent)
