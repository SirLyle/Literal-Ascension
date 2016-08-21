package com.jamieswhiteshirt.literalascension.server.network.message

import com.jamieswhiteshirt.literalascension.common.network.message.MessageBlockCarved
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

class DummyMessageHandler : IMessageHandler<MessageBlockCarved, IMessage> {
    override fun onMessage(message: MessageBlockCarved, ctx: MessageContext): IMessage? {
        return null
    }
}
