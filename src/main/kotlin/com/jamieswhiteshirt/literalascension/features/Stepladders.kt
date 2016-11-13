package com.jamieswhiteshirt.literalascension.features

import com.jamieswhiteshirt.literalascension.Features
import com.jamieswhiteshirt.literalascension.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.eventhandler.StepladderHarvestCheckEventHandler
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomainBiomesOPlenty
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomainMinecraft
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomainMiscellaneous
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Configuration

class Stepladders(config: Configuration, override val parent: Features) : SubFeatureCollection<StepladderDomain>("stepladders", parent) {
    val MINECRAFT = optionalOn(config, StepladderDomainMinecraft(config, this))
    val BIOMESOPLENTY = optionalOn(config, StepladderDomainBiomesOPlenty(config, this))
    val MISCELLANEOUS = optionalOn(config, StepladderDomainMiscellaneous(config, this))

    fun playLadderPickupSound(world: World, pos: BlockPos, player: EntityPlayer) {
        val state = world.getBlockState(pos)
        val soundType = state.block.getSoundType(state, world, pos, player)
        world.playSound(player, pos, soundType.stepSound, SoundCategory.BLOCKS, soundType.getVolume() / 2.0F, soundType.getPitch() * 0.8F)
    }

    override fun registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(StepladderHarvestCheckEventHandler)
    }
}
