package com.jamieswhiteshirt.literalascension.api

interface ICarveMaterial {
    val requiredCarvingToolLevel: Int
    val suitableCarvingToolLevel: Int
    val suitableToolDamageMultiplier: Int
    val unsuitableToolDamageMultiplier: Int
}
