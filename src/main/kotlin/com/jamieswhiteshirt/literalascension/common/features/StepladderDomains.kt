package com.jamieswhiteshirt.literalascension.common.features

import com.jamieswhiteshirt.literalascension.common.Features
import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.*
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.*
import net.minecraft.block.BlockPlanks
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

class StepladderDomains(config: Configuration, override val parent: Features) : SubFeatureCollection<StepladderDomain>("domains", parent) {
    val MINECRAFT = optionalOn(config, StepladderDomainMinecraft(config, this))
    val MISCELLANEOUS = optionalOn(config, StepladderDomainMiscellaneous(config, this))

    fun playLadderPickupSound(world: World, pos: BlockPos, player: EntityPlayer) {
        val state = world.getBlockState(pos)
        val soundType = state.block.getSoundType(state, world, pos, player)
        world.playSound(player, pos, soundType.stepSound, SoundCategory.BLOCKS, soundType.getVolume() / 2.0F, soundType.getPitch() * 0.8F)
    }
}
