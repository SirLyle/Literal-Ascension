package com.jamieswhiteshirt.literalascension.server

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.network.message.MessageBlockCarved
import com.jamieswhiteshirt.literalascension.server.network.message.DummyMessageHandler
import net.minecraftforge.fml.relauncher.Side

class ServerProxy : CommonProxy() {
    override fun registerRenderers() { }

    override fun registerMessages() {
        super.registerMessages()
        LiteralAscension.packetHandler.registerMessage(DummyMessageHandler(), MessageBlockCarved::class.java, MessageBlockCarved.DISCRIMINATOR, Side.CLIENT)
    }
}
