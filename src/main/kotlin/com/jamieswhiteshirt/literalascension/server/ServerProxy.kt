package com.jamieswhiteshirt.literalascension.server

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import com.jamieswhiteshirt.literalascension.common.network.messagehandler.DummyMessageHandler
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.NetworkRegistry
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
