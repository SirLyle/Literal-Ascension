package com.jamieswhiteshirt.literalascension.common

interface IFeature {
    val featureName: String

    fun register()

    fun registerRecipes()
}
