package com.jamieswhiteshirt.literalascension.client.network.message

import com.jamieswhiteshirt.literalascension.common.network.message.MessageBlockCarved
import net.minecraft.client.Minecraft
import net.minecraft.util.SoundCategory
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

class MessageBlockCarvedHandler : IMessageHandler<MessageBlockCarved, IMessage> {
    override fun onMessage(message: MessageBlockCarved, ctx: MessageContext): IMessage? {
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

    private fun process(message: MessageBlockCarved) {
        val world = Minecraft.getMinecraft().thePlayer.worldObj
        val pos = message.pos
        if (pos != null) {
            val state = world.getBlockState(pos)
            val soundType = state.block.soundType
            world.playSound(pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5, soundType.breakSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F, false)
        }
    }
}
