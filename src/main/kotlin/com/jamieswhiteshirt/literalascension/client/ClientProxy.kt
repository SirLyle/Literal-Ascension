package com.jamieswhiteshirt.literalascension.client

import com.jamieswhiteshirt.literalascension.Features
import com.jamieswhiteshirt.literalascension.common.CommonProxy
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
class ClientProxy : CommonProxy() {
    override fun preInit(features: Features, messageHandler: SimpleNetworkWrapper) {
        super.preInit(features, messageHandler)
        features.registerRenderers()
        features.registerClientMessages(messageHandler)
    }
}
