package com.jamieswhiteshirt.literalascension

import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

interface IFeature {
    val featureName: String

    fun register()

    fun registerRecipes()

    @SideOnly(Side.CLIENT)
    fun registerClientMessages(messageHandler: SimpleNetworkWrapper)

    @SideOnly(Side.SERVER)
    fun registerServerMessages(messageHandler: SimpleNetworkWrapper)

    fun registerEventHandlers()

    @SideOnly(Side.CLIENT)
    fun registerRenderers()
}

interface ISubFeature : IFeature {
    val subFeatureName: String

    val parent: IFeature
}

abstract class SubFeature(override val subFeatureName: String, override val parent: IFeature) : ISubFeature {
    override val featureName: String get() = "${parent.featureName}.$subFeatureName"

    override fun register() {}

    override fun registerRecipes() {}

    @SideOnly(Side.CLIENT)
    override fun registerClientMessages(messageHandler: SimpleNetworkWrapper) {}

    @SideOnly(Side.SERVER)
    override fun registerServerMessages(messageHandler: SimpleNetworkWrapper) {}

    override fun registerEventHandlers() {}

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {}
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

    @SideOnly(Side.CLIENT)
    override fun registerClientMessages(messageHandler: SimpleNetworkWrapper) {
        for (subFeature in subFeatures) {
            subFeature.registerClientMessages(messageHandler)
        }
    }

    @SideOnly(Side.SERVER)
    override fun registerServerMessages(messageHandler: SimpleNetworkWrapper) {
        for (subFeature in subFeatures) {
            subFeature.registerServerMessages(messageHandler)
        }
    }

    override fun registerRecipes() {
        for (subFeature in subFeatures) {
            subFeature.registerRecipes()
        }
    }

    override fun registerEventHandlers() {
        for (subFeature in subFeatures) {
            subFeature.registerEventHandlers()
        }
    }

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {
        for (subFeature in subFeatures) {
            subFeature.registerRenderers()
        }
    }
}

abstract class SubFeatureCollection<T : ISubFeature>(override val subFeatureName: String, override val parent: IFeature) : FeatureCollectionBase<T>(), ISubFeature {
    override val featureName: String get() = "${parent.featureName}.$subFeatureName"
}
