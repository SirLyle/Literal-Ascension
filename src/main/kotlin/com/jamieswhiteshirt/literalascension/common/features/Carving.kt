package com.jamieswhiteshirt.literalascension.common.features

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.common.Features
import com.jamieswhiteshirt.literalascension.common.ISubFeature
import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingMaterials
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingTools
import com.jamieswhiteshirt.literalascension.common.network.message.MessagePlayCarveSound
import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.network.NetworkRegistry

class Carving(config: Configuration, override val parent: Features) : SubFeatureCollection<ISubFeature>("carving", parent) {
    val CARVING_MATERIALS = required(CarvingMaterials(config, this))
    val CARVING_TOOLS     = required(CarvingTools(config, this))

    val carvingBehaviourShims = mutableMapOf<Block, ICarvingBehaviour>()

    init {
        for (carvingMaterial in CARVING_MATERIALS.subFeatures) {
            carvingBehaviourShims[carvingMaterial.block] = carvingMaterial.getCarvingBehaviourShim()
        }
    }

    fun playCarveSound(world: World, pos: BlockPos, entity: Entity) {
        val targetPoint = NetworkRegistry.TargetPoint(world.provider.dimension, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 16.0)
        LiteralAscension.packetHandler.sendToAllAround(MessagePlayCarveSound(pos, entity.entityId), targetPoint)
    }

    fun spawnCarveParticles(world: World, pos: BlockPos, facing: EnumFacing) {
        val targetPoint = NetworkRegistry.TargetPoint(world.provider.dimension, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), 16.0)
        LiteralAscension.packetHandler.sendToAllAround(MessageSpawnCarveParticles(pos, facing), targetPoint)
    }

    fun getCarvingBehaviour(state: IBlockState): ICarvingBehaviour? {
        val block = state.block
        return when (block) {
            is ICarvingBehaviour -> block
            else -> carvingBehaviourShims[block]
        }
    }
}
