package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.network.message.MessagePlayCarveSound
import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import net.minecraft.entity.Entity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.NetworkRegistry

fun World.playCarveSound(pos: BlockPos, entity: Entity) {
    val targetPoint = NetworkRegistry.TargetPoint(this.provider.dimension, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 64.0)
    LiteralAscension.packetHandler.sendToAllAround(MessagePlayCarveSound(pos, entity.entityId), targetPoint)
}

fun World.spawnCarveParticles(pos: BlockPos, facing: EnumFacing) {
    val targetPoint = NetworkRegistry.TargetPoint(this.provider.dimension, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 64.0)
    LiteralAscension.packetHandler.sendToAllAround(MessageSpawnCarveParticles(pos, facing), targetPoint)
}
