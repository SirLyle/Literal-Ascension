package com.jamieswhiteshirt.literalascension.common.features

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.api.ICarvingBehaviour
import com.jamieswhiteshirt.literalascension.common.Features
import com.jamieswhiteshirt.literalascension.common.ISubFeature
import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingDomains
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingTools
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

class Carving(config: Configuration, override val parent: Features) : SubFeatureCollection<ISubFeature>("carving", parent) {
    private val carvingBehaviourShims = mutableMapOf<Block, ICarvingBehaviour>()

    val CARVING_DOMAINS = required(CarvingDomains(config, this))
    val CARVING_TOOLS   = optionalOn(config, CarvingTools(config, this))

    fun playCarveSound(world: World, pos: BlockPos, player: EntityPlayer) {
        val state = world.getBlockState(pos)
        val soundType = state.block.getSoundType(state, world, pos, player)
        world.playSound(player, pos, soundType.breakSound, SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F)
    }

    fun spawnCarveParticles(world: World, pos: BlockPos, facing: EnumFacing) {
        LiteralAscension.PROXY.spawnCarveParticles(world, pos, facing)
    }

    fun getCarvingBehaviour(state: IBlockState): ICarvingBehaviour? {
        val block = state.block
        return when (block) {
            is ICarvingBehaviour -> block
            else -> carvingBehaviourShims[block]
        }
    }

    fun registerCarvingBehaviour(block: Block, carvingBehaviour: ICarvingBehaviour) {
        carvingBehaviourShims[block] = carvingBehaviour
    }
}
