package com.jamieswhiteshirt.literalascension.common.config

import net.ilexiconn.llibrary.server.config.ConfigEntry

class LiteralAscensionConfig {
    @JvmField
    @ConfigEntry(category = "carving.wood", comment = "Required tool level for carving of wood", minValue = "0")
    var woodRequiredCarvingToolLevel: Int = 0

    @JvmField
    @ConfigEntry(category = "carving.wood", comment = "Viable tool level for carving of wood", minValue = "0")
    var woodViableCarvingToolLevel: Int = 0

    @JvmField
    @ConfigEntry(category = "carving.wood", comment = "Tool damage multiplier for viable carving of wood", minValue = "0")
    var woodViableToolDamageMultiplier: Int = 1

    @JvmField
    @ConfigEntry(category = "carving.wood", comment = "Tool damage multiplier for unviable carving of wood", minValue = "0")
    var woodUnviableToolDamageMultiplier: Int = 10

    @JvmField
    @ConfigEntry(category = "carving.stone", comment = "Required tool level for carving of stone", minValue = "0")
    var stoneRequiredCarvingToolLevel: Int = 3

    @JvmField
    @ConfigEntry(category = "carving.stone", comment = "Viable tool level for carving of stone", minValue = "0")
    var stoneViableCarvingToolLevel: Int = 3

    @JvmField
    @ConfigEntry(category = "carving.stone", comment = "Tool damage multiplier for viable carving of stone", minValue = "0")
    var stoneViableToolDamageMultiplier: Int = 1

    @JvmField
    @ConfigEntry(category = "carving.stone", comment = "Tool damage multiplier for unviable carving of stone", minValue = "0")
    var stoneUnviableToolDamageMultiplier: Int = 10
}