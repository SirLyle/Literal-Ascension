package com.jamieswhiteshirt.literalascension.common

import net.minecraftforge.common.config.Configuration
import java.util.*

interface IFeature {
    val featureName: String

    fun register()

    fun registerRecipes()
}

interface ISubFeature : IFeature {
    val subFeatureName: String

    val parent: IFeature
}

abstract class SubFeature(override val subFeatureName: String, override val parent: IFeature) : ISubFeature {
    override val featureName: String get() = "${parent.featureName}.$subFeatureName"
}

abstract class FeatureCollectionBase<T : ISubFeature> : IFeature {
    private val _subFeatures = ArrayList<T>()

    val subFeatures: List<T> get() = _subFeatures

    protected fun <U : T> optionalOn(config: Configuration, feature: U?): U? {
        return optional(config, feature, true)
    }

    protected fun <U : T> optionalOff(config: Configuration, feature: U?): U? {
        return optional(config, feature, false)
    }

    protected fun <U: T> optional(config: Configuration, feature: U?, default: Boolean): U? {
        if (feature != null) {
            if (config.getBoolean("enable", feature.featureName, default, "Enable this feature")) {
                return required(feature)
            }
        }
        return null
    }

    protected fun <U : T> required(feature: U): U {
        _subFeatures.add(feature)
        return feature
    }

    override fun register() {
        for (subFeature in subFeatures) {
            subFeature.register()
        }
    }

    override fun registerRecipes() {
        for (subFeature in subFeatures) {
            subFeature.registerRecipes()
        }
    }
}

abstract class SubFeatureCollection<T : ISubFeature>(override val subFeatureName: String, override val parent: IFeature) : FeatureCollectionBase<T>(), ISubFeature {
    override val featureName: String get() = "${parent.featureName}.$subFeatureName"
}
