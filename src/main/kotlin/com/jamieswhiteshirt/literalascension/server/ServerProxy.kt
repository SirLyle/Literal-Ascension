package com.jamieswhiteshirt.literalascension.server

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.network.message.MessagePlayCarveSound
import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import com.jamieswhiteshirt.literalascension.server.network.message.DummyMessageHandler
import net.minecraftforge.fml.relauncher.Side

class ServerProxy : CommonProxy() {
    override fun registerRenderers() { }

    override fun registerMessages() {
        super.registerMessages()
        LiteralAscension.packetHandler.registerMessage(DummyMessageHandler, MessagePlayCarveSound::class.java, MessagePlayCarveSound.DISCRIMINATOR, Side.CLIENT)
        LiteralAscension.packetHandler.registerMessage(DummyMessageHandler, MessageSpawnCarveParticles::class.java, MessageSpawnCarveParticles.DISCRIMINATOR, Side.CLIENT)
    }
}
