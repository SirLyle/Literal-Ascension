package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains

import com.jamieswhiteshirt.literalascension.common.features.StepladderDomains
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.StepladderNewWood
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.StepladderOldWood
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder.StepladderSimple
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.common.config.Configuration

class StepladderDomainMinecraft(config: Configuration, parent: StepladderDomains) : StepladderDomain("minecraft", parent) {
    val OAK          = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.OAK, this))
    val SPRUCE       = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.SPRUCE, this))
    val BIRCH        = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.BIRCH, this))
    val JUNGLE       = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.JUNGLE, this))
    val ACACIA       = optionalOn(config, StepladderNewWood(BlockPlanks.EnumType.ACACIA, this))
    val DARK_OAK     = optionalOn(config, StepladderNewWood(BlockPlanks.EnumType.DARK_OAK, this))
    val IRON         = optionalOn(config, StepladderSimple(Blocks.IRON_BLOCK.defaultState, "iron", ItemStack(Items.IRON_INGOT), this))
    val GOLD         = optionalOff(config, StepladderSimple(Blocks.GOLD_BLOCK.defaultState, "gold", ItemStack(Items.GOLD_INGOT), this))
    val DIAMOND      = optionalOff(config, StepladderSimple(Blocks.DIAMOND_BLOCK.defaultState, "diamond", ItemStack(Items.DIAMOND), this))
    val EMERALD      = optionalOff(config, StepladderSimple(Blocks.EMERALD_BLOCK.defaultState, "emerald", ItemStack(Items.EMERALD), this))
}
