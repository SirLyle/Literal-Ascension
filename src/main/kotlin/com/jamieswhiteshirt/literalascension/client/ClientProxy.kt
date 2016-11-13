package com.jamieswhiteshirt.literalascension.client

import com.jamieswhiteshirt.literalascension.Features
import com.jamieswhiteshirt.literalascension.common.CommonProxy
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper

class ClientProxy : CommonProxy() {
    override fun preInit(features: Features, messageHandler: SimpleNetworkWrapper) {
        super.preInit(features, messageHandler)
        features.registerRenderers()
        features.registerClientMessages(messageHandler)
    }
}
