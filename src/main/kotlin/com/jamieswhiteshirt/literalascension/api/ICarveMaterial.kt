package com.jamieswhiteshirt.literalascension.api

interface ICarveMaterial {
    /**
     * The required tool level for carving blocks of this material.
     * If the tool's level is below this level, carving will not happen.
     */
    val requiredCarvingToolLevel: Int

    /**
     * The suitable tool level for carving blocks of this material.
     * If the tool's level is less than this level when carving, unsuitableToolDamageMultiplier is applied.
     * If the tool's level is greater than or equal to this level when carving, suitableToolDamageMultiplier is applied.
     */
    val suitableCarvingToolLevel: Int

    /**
     * The tool damage multiplier applied if the tool's level is greater than or equal to suitableCarvingToolLevel when carving.
     */
    val suitableToolDamageMultiplier: Int

    /**
     * The tool damage multiplier applied if the tool's level is less than suitableCarvingToolLevel when carving.
     */
    val unsuitableToolDamageMultiplier: Int
}
