package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.common.features.ClimbingRope
import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraftforge.common.config.Configuration

class Features(config: Configuration) : FeatureCollectionBase<ISubFeature>() {
    override val featureName: String get() = "features"

    val CARVING = optionalOn(config, Carving(config, this))
    val CLIMBING_ROPE = optionalOn(config, ClimbingRope(this))
    val STEPLADDERS = optionalOn(config, Stepladders(config, this))
}
