package com.jamieswhiteshirt.literalascension.client.network.message

import com.jamieswhiteshirt.literalascension.common.network.message.MessagePlayCarveSound
import net.minecraft.client.Minecraft
import net.minecraft.util.SoundCategory
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

object MessagePlayCarveSoundHandler : IMessageHandler<MessagePlayCarveSound, IMessage> {
    override fun onMessage(message: MessagePlayCarveSound, ctx: MessageContext): IMessage? {
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

    private fun process(message: MessagePlayCarveSound) {
        val pos = message.pos
        if (pos != null) {
            val world = Minecraft.getMinecraft().thePlayer.worldObj
            val state = world.getBlockState(pos)
            val soundType = state.block.soundType
            world.playSound(pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5, soundType.breakSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F, false)
        }
    }
}
