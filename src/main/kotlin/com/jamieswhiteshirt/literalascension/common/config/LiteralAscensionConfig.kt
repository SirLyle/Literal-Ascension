package com.jamieswhiteshirt.literalascension.common.config

import net.ilexiconn.llibrary.server.config.ConfigEntry

class LiteralAscensionConfig {
    @JvmField
    @ConfigEntry(category = "carving.material.wood", comment = "Required tool level for carving of wood", minValue = "0")
    var woodRequiredCarvingToolLevel: Int = 0

    @JvmField
    @ConfigEntry(category = "carving.material.wood", comment = "Suitable tool level for carving of wood", minValue = "0")
    var woodSuitableCarvingToolLevel: Int = 0

    @JvmField
    @ConfigEntry(category = "carving.material.wood", comment = "Tool damage multiplier for suitable carving of wood", minValue = "0")
    var woodSuitableToolDamageMultiplier: Int = 1

    @JvmField
    @ConfigEntry(category = "carving.material.wood", comment = "Tool damage multiplier for unsuitable carving of wood", minValue = "0")
    var woodUnsuitableToolDamageMultiplier: Int = 10

    @JvmField
    @ConfigEntry(category = "carving.material.stone", comment = "Required tool level for carving of stone", minValue = "0")
    var stoneRequiredCarvingToolLevel: Int = 3

    @JvmField
    @ConfigEntry(category = "carving.material.stone", comment = "Suitable tool level for carving of stone", minValue = "0")
    var stoneSuitableCarvingToolLevel: Int = 3

    @JvmField
    @ConfigEntry(category = "carving.material.stone", comment = "Tool damage multiplier for suitable carving of stone", minValue = "0")
    var stoneSuitableToolDamageMultiplier: Int = 1

    @JvmField
    @ConfigEntry(category = "carving.material.stone", comment = "Tool damage multiplier for unsuitable carving of stone", minValue = "0")
    var stoneUnsuitableToolDamageMultiplier: Int = 10
}