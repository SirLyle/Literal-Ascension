package com.jamieswhiteshirt.literalascension.common.network.messagehandler

import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

object DummyMessageHandler : IMessageHandler<IMessage, IMessage> {
    override fun onMessage(message: IMessage, ctx: MessageContext): IMessage? {
        return null
    }
}
