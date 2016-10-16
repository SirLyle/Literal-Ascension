package com.jamieswhiteshirt.literalascension.common.carvedblock

import com.jamieswhiteshirt.literalascension.api.ICarveMaterial

class CarvedBlockMaterial(
        val hardness: Float,
        override val requiredCarvingToolLevel: Int,
        override val suitableCarvingToolLevel: Int,
        override val suitableToolDamageMultiplier: Int,
        override val unsuitableToolDamageMultiplier: Int
) : ICarveMaterial
