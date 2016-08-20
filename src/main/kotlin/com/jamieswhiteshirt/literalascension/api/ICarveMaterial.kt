package com.jamieswhiteshirt.literalascension.api

interface ICarveMaterial {
    val requiredCarvingToolLevel: Int
    val viableCarvingToolLevel: Int
    val viableToolDamageMultiplier: Int
    val unviableToolDamageMultiplier: Int
}
