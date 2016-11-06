package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.StepladderGenericWood
import net.minecraftforge.common.config.Configuration

class StepladderDomainMiscellaneous(config: Configuration, parent: Stepladders) : StepladderDomain("miscellaneous", parent) {
    val GENERIC_WOOD = optionalOff(config, StepladderGenericWood(this))
}
