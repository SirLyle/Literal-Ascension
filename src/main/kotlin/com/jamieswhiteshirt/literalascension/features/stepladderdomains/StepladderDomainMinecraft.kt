package com.jamieswhiteshirt.literalascension.features.stepladderdomains

import com.jamieswhiteshirt.literalascension.features.Stepladders
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder.StepladderNewWood
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder.StepladderOldWood
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder.StepladderMaterial
import net.minecraft.block.BlockPlanks
import net.minecraft.block.material.Material
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.common.config.Configuration

class StepladderDomainMinecraft(config: Configuration, parent: Stepladders) : StepladderDomain("minecraft", parent) {
    val OAK      = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.OAK, this))
    val SPRUCE   = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.SPRUCE, this))
    val BIRCH    = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.BIRCH, this))
    val JUNGLE   = optionalOn(config, StepladderOldWood(BlockPlanks.EnumType.JUNGLE, this))
    val ACACIA   = optionalOn(config, StepladderNewWood(BlockPlanks.EnumType.ACACIA, this))
    val DARK_OAK = optionalOn(config, StepladderNewWood(BlockPlanks.EnumType.DARK_OAK, this))
    val IRON     = optionalOn(config, StepladderMaterial(ItemStack(Items.IRON_INGOT), "iron", this))
    val GOLD     = optionalOff(config, StepladderMaterial(ItemStack(Items.GOLD_INGOT), "gold", this))
    val DIAMOND  = optionalOff(config, StepladderMaterial(ItemStack(Items.DIAMOND), "diamond", this))
    val EMERALD  = optionalOff(config, StepladderMaterial(ItemStack(Items.EMERALD), "emerald", this))
}
