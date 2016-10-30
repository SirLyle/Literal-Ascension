package com.jamieswhiteshirt.literalascension.common

interface ISubFeature : IFeature {
    val subFeatureName: String

    val parent: IFeature
}

abstract class SubFeature(override val subFeatureName: String, override val parent: IFeature) : ISubFeature {
    override val featureName: String get() = "${parent.featureName}.$subFeatureName"
}
