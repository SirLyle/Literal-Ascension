package com.jamieswhiteshirt.literalascension

import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.config.LiteralAscensionConfig
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import com.jamieswhiteshirt.literalascension.common.init.LAItems
import com.jamieswhiteshirt.literalascension.common.init.LARecipes
import net.ilexiconn.llibrary.server.config.Config
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper

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

        const val CLIENT_PROXY = "com.jamieswhiteshirt.literalascension.client.ClientProxy"
        const val SERVER_PROXY = "com.jamieswhiteshirt.literalascension.server.ServerProxy"

        @SidedProxy(
                clientSide = CLIENT_PROXY,
                serverSide = SERVER_PROXY,
                modId = MODID
        )
        lateinit var proxy: CommonProxy

        @Config
        lateinit var config: LiteralAscensionConfig

        val packetHandler: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        LABlocks.register()
        LAItems.register()

        proxy.registerMessages()
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.registerRenderers()
        proxy.registerEventHandlers()

        LARecipes.register()
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
    }
}
