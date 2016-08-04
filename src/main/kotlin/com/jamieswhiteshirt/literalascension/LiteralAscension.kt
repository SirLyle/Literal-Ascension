package com.jamieswhiteshirt.literalascension

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(
        modid = LiteralAscension.MODID,
        name = LiteralAscension.NAME,
        version = LiteralAscension.VERSION,
        dependencies = LiteralAscension.DEPENDENCIES,
        acceptedMinecraftVersions = LiteralAscension.ACCEPTED_MINECRAFT_VERSIONS
)
class LiteralAscension {
    companion object {
        const val MODID = "literalascension"
        const val NAME = "Literal Ascension"
        const val VERSION = "1.0"
        const val DEPENDENCIES = "required-after:llibrary@[1.5.1,)"
        const val ACCEPTED_MINECRAFT_VERSIONS = "[1.10,1.10.2]"
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
    }
}
