package com.jamieswhiteshirt.literalascension.client.network.messagehandler

import com.jamieswhiteshirt.literalascension.common.network.message.MessagePlayLadderPickupSound
import net.minecraft.block.SoundType
import net.minecraft.client.Minecraft
import net.minecraft.util.SoundCategory
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

object MessagePlayLadderPickupSoundHandler : IMessageHandler<MessagePlayLadderPickupSound, IMessage> {
    override fun onMessage(message: MessagePlayLadderPickupSound, ctx: MessageContext): IMessage? {
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

    private fun process(message: MessagePlayLadderPickupSound) {
        val pos = message.pos
        if (pos != null) {
            val world = Minecraft.getMinecraft().thePlayer.worldObj
            val soundType = SoundType.LADDER
            world.playSound(pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5, soundType.stepSound, SoundCategory.BLOCKS, soundType.getVolume() / 2.0F, soundType.getPitch() * 0.8F, false)
        }
    }
}
