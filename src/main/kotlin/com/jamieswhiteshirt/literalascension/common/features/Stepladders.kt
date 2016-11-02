package com.jamieswhiteshirt.literalascension.common.features

import com.jamieswhiteshirt.literalascension.common.Features
import com.jamieswhiteshirt.literalascension.common.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.common.features.stepladders.*
import net.minecraft.block.BlockPlanks
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.config.Configuration

class Stepladders(config: Configuration, override val parent: Features) : SubFeatureCollection<Stepladder>("stepladders", parent) {
    val OAK          = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.OAK, this))
    val SPRUCE       = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.SPRUCE, this))
    val BIRCH        = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.BIRCH, this))
    val JUNGLE       = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.JUNGLE, this))
    val ACACIA       = optionalOn(config, StepladderNewWood(BlockPlanks.EnumType.ACACIA, this))
    val DARK_OAK     = optionalOn(config, StepladderNewWood(BlockPlanks.EnumType.DARK_OAK, this))
    val IRON         = optionalOn(config, StepladderSimple(Material.IRON, "iron", ItemStack(Items.IRON_INGOT), this))
    val GOLD         = optionalOff(config, StepladderSimple(Material.IRON, "gold", ItemStack(Items.GOLD_INGOT), this))
    val DIAMOND      = optionalOff(config, StepladderSimple(Material.IRON, "diamond", ItemStack(Items.DIAMOND), this))
    val EMERALD      = optionalOff(config, StepladderSimple(Material.IRON, "emerald", ItemStack(Items.EMERALD), this))
    val GENERIC_WOOD = optionalOff(config, StepladderGenericWood(this))

    fun playLadderPickupSound(world: World, pos: BlockPos, player: EntityPlayer) {
        val state = world.getBlockState(pos)
        val soundType = state.block.getSoundType(state, world, pos, player)
        world.playSound(player, pos, soundType.stepSound, SoundCategory.BLOCKS, soundType.getVolume() / 2.0F, soundType.getPitch() * 0.8F)
    }
}
