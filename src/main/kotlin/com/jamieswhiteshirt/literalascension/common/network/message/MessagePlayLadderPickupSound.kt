package com.jamieswhiteshirt.literalascension.common.network.message

import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class MessagePlayLadderPickupSound(var pos: BlockPos?) : IMessage {
    companion object {
        const val DISCRIMINATOR = 2
    }

    constructor() : this(null)

    override fun fromBytes(buf: ByteBuf) {
        this.pos = BlockPos(buf.readInt(), buf.readInt(), buf.readInt())
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(pos!!.x)
        buf.writeInt(pos!!.y)
        buf.writeInt(pos!!.z)
    }
}
