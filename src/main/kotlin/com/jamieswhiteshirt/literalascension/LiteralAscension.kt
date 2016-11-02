package com.jamieswhiteshirt.literalascension

import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.Features
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper

@Mod(
        modid = LiteralAscension.MODID,
        name = LiteralAscension.NAME,
        version = LiteralAscension.VERSION,
        dependencies = LiteralAscension.DEPENDENCIES,
        acceptedMinecraftVersions = LiteralAscension.ACCEPTED_MINECRAFT_VERSIONS,
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
)
object LiteralAscension {
    const val MODID = "literalascension"
    const val NAME = "Literal Ascension"
    const val VERSION = "1.10.2-0.0.1.0"
    const val DEPENDENCIES = "required-after:llibrary@[1.7.0,)"
    const val ACCEPTED_MINECRAFT_VERSIONS = "[1.10,1.10.2]"

    const val CLIENT_PROXY = "com.jamieswhiteshirt.literalascension.client.ClientProxy"
    const val SERVER_PROXY = "com.jamieswhiteshirt.literalascension.server.ServerProxy"

    @SidedProxy(
            clientSide = CLIENT_PROXY,
            serverSide = SERVER_PROXY,
            modId = MODID
    )
    lateinit var PROXY: CommonProxy

    lateinit var FEATURES: Features

    val packetHandler: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)

    @Mod.EventHandler
    fun preInit(@Suppress("UNUSED_PARAMETER") event: FMLPreInitializationEvent) {
        FEATURES = Features(Configuration(event.suggestedConfigurationFile))

        FEATURES.register()

        PROXY.registerMessages()
    }

    @Mod.EventHandler
    fun init(@Suppress("UNUSED_PARAMETER") event: FMLInitializationEvent) {
        PROXY.registerRenderers()
        PROXY.registerEventHandlers()

        FEATURES.registerRecipes()
    }
}
