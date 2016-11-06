package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains

import com.jamieswhiteshirt.literalascension.common.features.StepladderDomains
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.StepladderGenericWood
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.StepladderNewWood
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.StepladderOldWood
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.StepladderSimple
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.common.config.Configuration

class StepladderDomainMiscellaneous(config: Configuration, parent: StepladderDomains) : StepladderDomain("miscellaneous", parent) {
    val GENERIC_WOOD = optionalOff(config, StepladderGenericWood(this))
}
