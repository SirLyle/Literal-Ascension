package com.jamieswhiteshirt.literalascension.common.network.message

import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class MessagePlayCarveSound(var pos: BlockPos?, var entityID: Int?) : IMessage {
    companion object {
        const val DISCRIMINATOR = 0
    }

    constructor() : this(null, null)

    override fun fromBytes(buf: ByteBuf) {
        this.pos = BlockPos(buf.readInt(), buf.readInt(), buf.readInt())
        this.entityID = buf.readInt()
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(pos!!.x)
        buf.writeInt(pos!!.y)
        buf.writeInt(pos!!.z)
        buf.writeInt(entityID!!)
    }
}
