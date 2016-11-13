package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.Features
import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper

abstract class CommonProxy {
    open fun preInit(features: Features, messageHandler: SimpleNetworkWrapper) {
        features.register()
    }

    open fun init(features: Features) {
        features.registerRecipes()
        features.registerEventHandlers()
    }

    fun spawnCarveParticles(world: World, pos: BlockPos, facing: EnumFacing) {
        val targetPoint = NetworkRegistry.TargetPoint(world.provider.dimension, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 16.0)
        LiteralAscension.PACKET_HANDLER.sendToAllAround(MessageSpawnCarveParticles(pos, facing), targetPoint)
    }
}
