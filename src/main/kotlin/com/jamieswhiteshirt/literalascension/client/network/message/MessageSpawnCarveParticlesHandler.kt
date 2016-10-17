package com.jamieswhiteshirt.literalascension.client.network.message

import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

object MessageSpawnCarveParticlesHandler : IMessageHandler<MessageSpawnCarveParticles, IMessage> {
    override fun onMessage(message: MessageSpawnCarveParticles, ctx: MessageContext): IMessage? {
        val thread = FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
        if (thread.isCallingFromMinecraftThread) {
            process(message)
        } else {
            thread.addScheduledTask {
                process(message)
            }
        }
        return null
    }

    private fun process(message: MessageSpawnCarveParticles) {
        val pos = message.pos
        val facing = message.facing
        if (pos != null && facing != null) {
            val effectRenderer = Minecraft.getMinecraft().effectRenderer
            for (i in 0..15) {
                effectRenderer.addBlockHitEffects(pos, facing)
            }
        }
    }
}
