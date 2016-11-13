package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.eventhandler.HarvestCheckEventHandler
import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.network.NetworkRegistry

abstract class CommonProxy {
    abstract fun registerRenderers()

    open fun registerEventHandlers() {
        LiteralAscension.FEATURES.STEPLADDERS?.let {
            MinecraftForge.EVENT_BUS.register(HarvestCheckEventHandler)
        }
        LiteralAscension.FEATURES.CARVING?.TCONSTRUCT_CARVING?.let {
            it.interop.registerEventHandlers(it.parent)
        }
    }

    open fun registerMessages() { }

    fun spawnCarveParticles(world: World, pos: BlockPos, facing: EnumFacing) {
        val targetPoint = NetworkRegistry.TargetPoint(world.provider.dimension, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 16.0)
        LiteralAscension.packetHandler.sendToAllAround(MessageSpawnCarveParticles(pos, facing), targetPoint)
    }
}
