package com.jamieswhiteshirt.literalascension.features.stepladderdomains

import com.jamieswhiteshirt.literalascension.features.Stepladders
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder.StepladderWoodBase
import net.minecraftforge.common.config.Configuration

class StepladderDomainMiscellaneous(config: Configuration, parent: Stepladders) : StepladderDomain("miscellaneous", parent) {
    val GENERIC_WOOD = optionalOff(config, object : StepladderWoodBase("wood", this) {
        override val feetIngredient: Any = "logWood"
        override val stepsIngredient: Any = "plankWood"
    })
}
