package com.jamieswhiteshirt.literalascension

import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.config.LiteralAscensionConfig
import com.jamieswhiteshirt.literalascension.common.init.*
import net.ilexiconn.llibrary.server.config.Config
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
        guiFactory = LiteralAscension.GUI_FACTORY,
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter"
)
object LiteralAscension {
    const val MODID = "literalascension"
    const val NAME = "Literal Ascension"
    const val VERSION = "1.10.2-1.0.0.0"
    const val DEPENDENCIES = "required-after:llibrary@[1.7.0,)"
    const val ACCEPTED_MINECRAFT_VERSIONS = "[1.10,1.10.2]"

    const val GUI_FACTORY = "com.jamieswhiteshirt.literalascension.client.gui.LiteralAscensionGUIFactory"
    const val CLIENT_PROXY = "com.jamieswhiteshirt.literalascension.client.ClientProxy"
    const val SERVER_PROXY = "com.jamieswhiteshirt.literalascension.server.ServerProxy"

    @SidedProxy(
            clientSide = CLIENT_PROXY,
            serverSide = SERVER_PROXY,
            modId = MODID
    )
    lateinit var proxy: CommonProxy

    @JvmStatic
    @Config
    lateinit var config: LiteralAscensionConfig

    val packetHandler: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID)

    @Mod.EventHandler
    fun preInit(@Suppress("UNUSED_PARAMETER") event: FMLPreInitializationEvent) {
        Stepladders.register()
        CarvingTools.register()
        CarvedBlocks.register()
        ClimbingRope.register()

        proxy.registerMessages()
    }

    @Mod.EventHandler
    fun init(@Suppress("UNUSED_PARAMETER") event: FMLInitializationEvent) {
        proxy.registerRenderers()
        proxy.registerEventHandlers()

        Stepladders.registerRecipes()
        CarvingTools.registerRecipes()
        CarvedBlocks.registerRecipes()
        ClimbingRope.registerRecipes()
    }
}
