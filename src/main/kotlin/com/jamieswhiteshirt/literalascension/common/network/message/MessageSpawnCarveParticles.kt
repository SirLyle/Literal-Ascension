package com.jamieswhiteshirt.literalascension.common.network.message

import io.netty.buffer.ByteBuf
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class MessageSpawnCarveParticles(var pos: BlockPos?, var facing: EnumFacing?) : IMessage {
    companion object {
        const val DISCRIMINATOR = 1
    }

    constructor() : this(null, null)

    override fun fromBytes(buf: ByteBuf) {
        this.pos = BlockPos(buf.readInt(), buf.readInt(), buf.readInt())
        this.facing = EnumFacing.VALUES.elementAtOrNull(buf.readUnsignedByte().toInt())
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeInt(pos!!.x)
        buf.writeInt(pos!!.y)
        buf.writeInt(pos!!.z)
        buf.writeByte(facing!!.index)
    }
}
