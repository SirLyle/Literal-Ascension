package com.jamieswhiteshirt.literalascension.common.config

import net.ilexiconn.llibrary.server.config.ConfigEntry

class LiteralAscensionConfig {
    @JvmField
    @ConfigEntry(category = "carving.material.wood", comment = "Required tool level for carving of wood", minValue = "0")
    var woodRequiredCarvingToolLevel = 0

    @JvmField
    @ConfigEntry(category = "carving.material.wood", comment = "Suitable tool level for carving of wood", minValue = "0")
    var woodSuitableCarvingToolLevel = 0

    @JvmField
    @ConfigEntry(category = "carving.material.wood", comment = "Tool damage multiplier for suitable carving of wood", minValue = "0")
    var woodSuitableToolDamageMultiplier = 1

    @JvmField
    @ConfigEntry(category = "carving.material.wood", comment = "Tool damage multiplier for unsuitable carving of wood", minValue = "0")
    var woodUnsuitableToolDamageMultiplier = 10

    @JvmField
    @ConfigEntry(category = "carving.material.stone", comment = "Required tool level for carving of stone", minValue = "0")
    var stoneRequiredCarvingToolLevel = 3

    @JvmField
    @ConfigEntry(category = "carving.material.stone", comment = "Suitable tool level for carving of stone", minValue = "0")
    var stoneSuitableCarvingToolLevel = 3

    @JvmField
    @ConfigEntry(category = "carving.material.stone", comment = "Tool damage multiplier for suitable carving of stone", minValue = "0")
    var stoneSuitableToolDamageMultiplier = 1

    @JvmField
    @ConfigEntry(category = "carving.material.stone", comment = "Tool damage multiplier for unsuitable carving of stone", minValue = "0")
    var stoneUnsuitableToolDamageMultiplier = 10

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Oak Wood Stepladder")
    var stepladderOakEnabled = true

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Spruce Wood Stepladder")
    var stepladderSpruceEnabled = true

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Birch Wood Stepladder")
    var stepladderBirchEnabled = true

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Jungle Wood Stepladder")
    var stepladderJungleEnabled = true

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Acacia Wood Stepladder")
    var stepladderAcaciaEnabled = true

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Dark Oak Wood Stepladder")
    var stepladderDarkOakEnabled = true

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Iron Stepladder")
    var stepladderIronEnabled = true

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Gold Stepladder")
    var stepladderGoldEnabled = false

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Diamond Stepladder")
    var stepladderDiamondEnabled = false

    @JvmField
    @ConfigEntry(category = "stepladder.materials", comment = "Enable Emerald Stepladder")
    var stepladderEmeraldEnabled = false
}