package com.jamieswhiteshirt.literalascension.server

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import com.jamieswhiteshirt.literalascension.common.network.messagehandler.DummyMessageHandler
import net.minecraftforge.fml.relauncher.Side

class ServerProxy : CommonProxy() {
    override fun registerRenderers() { }

    override fun registerMessages() {
        super.registerMessages()
        LiteralAscension.FEATURES.let {
            it.CARVING?.let {
                LiteralAscension.packetHandler.registerMessage(DummyMessageHandler, MessageSpawnCarveParticles::class.java, MessageSpawnCarveParticles.DISCRIMINATOR, Side.CLIENT)
            }
        }
    }
}
