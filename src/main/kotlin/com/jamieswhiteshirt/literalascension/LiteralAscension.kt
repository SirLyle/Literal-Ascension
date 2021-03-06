package com.jamieswhiteshirt.literalascension

import com.jamieswhiteshirt.literalascension.common.CommonProxy
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
    const val VERSION = "1.12.2-1.0.2.2"
    const val DEPENDENCIES = "required-after:forgelin@[1.8.2,);after:chisel;after:environmentaltech;after:BiomesOPlenty"
    const val ACCEPTED_MINECRAFT_VERSIONS = "[1.12,1.12.2]"

    const val CLIENT_PROXY = "com.jamieswhiteshirt.literalascension.client.ClientProxy"
    const val SERVER_PROXY = "com.jamieswhiteshirt.literalascension.server.ServerProxy"

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY, modId = MODID)
    lateinit var PROXY: CommonProxy

    lateinit var FEATURES: Features

    val PACKET_HANDLER: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        Configuration(event.suggestedConfigurationFile).let {
            FEATURES = Features(it)
            it.save()
        }

        PROXY.preInit(FEATURES, PACKET_HANDLER)
    }

    @Mod.EventHandler
    fun init(@Suppress("UNUSED_PARAMETER") event: FMLInitializationEvent) {
        PROXY.init(FEATURES)
    }
}
