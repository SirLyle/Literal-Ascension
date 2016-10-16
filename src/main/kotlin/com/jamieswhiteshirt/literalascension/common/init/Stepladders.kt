package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.stepladder.*
import net.minecraft.block.BlockPlanks
import net.minecraft.block.material.Material
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.*

object Stepladders : Module {
    val OAK = StepladderOldWood(BlockPlanks.EnumType.OAK)
    val SPRUCE = StepladderOldWood(BlockPlanks.EnumType.SPRUCE)
    val BIRCH = StepladderOldWood(BlockPlanks.EnumType.BIRCH)
    val JUNGLE = StepladderOldWood(BlockPlanks.EnumType.JUNGLE)
    val ACACIA = StepladderNewWood(BlockPlanks.EnumType.ACACIA)
    val DARK_OAK = StepladderNewWood(BlockPlanks.EnumType.DARK_OAK)
    val IRON = StepladderSimple(Material.IRON, "iron", ItemStack(Items.IRON_INGOT))
    val GOLD = StepladderSimple(Material.IRON, "gold", ItemStack(Items.GOLD_INGOT))
    val DIAMOND = StepladderSimple(Material.IRON, "diamond", ItemStack(Items.DIAMOND))
    val EMERALD = StepladderSimple(Material.IRON, "emerald", ItemStack(Items.EMERALD))
    val GENERIC_WOOD = StepladderGenericWood()

    private val registeredStepladders = ArrayList<Stepladder>()

    fun getRegisteredStepladders(): List<Stepladder> = registeredStepladders

    override fun register() {
        if (LiteralAscension.config.stepladderOakEnabled) registerStepladder(OAK)
        if (LiteralAscension.config.stepladderSpruceEnabled) registerStepladder(SPRUCE)
        if (LiteralAscension.config.stepladderBirchEnabled) registerStepladder(BIRCH)
        if (LiteralAscension.config.stepladderJungleEnabled) registerStepladder(JUNGLE)
        if (LiteralAscension.config.stepladderAcaciaEnabled) registerStepladder(ACACIA)
        if (LiteralAscension.config.stepladderDarkOakEnabled) registerStepladder(DARK_OAK)
        if (LiteralAscension.config.stepladderIronEnabled) registerStepladder(IRON)
        if (LiteralAscension.config.stepladderGoldEnabled) registerStepladder(GOLD)
        if (LiteralAscension.config.stepladderDiamondEnabled) registerStepladder(DIAMOND)
        if (LiteralAscension.config.stepladderEmeraldEnabled) registerStepladder(EMERALD)
        if (LiteralAscension.config.stepladderGenericWoodEnabled) registerStepladder(GENERIC_WOOD)
    }

    private fun registerStepladder(stepladder: Stepladder) {
        stepladder.item.creativeTab = CreativeTab

        GameRegistry.register(stepladder.block.setRegistryName("literalascension", "${stepladder.name}_stepladder"))
        GameRegistry.register(stepladder.item.setRegistryName("literalascension", "${stepladder.name}_stepladder"))

        registeredStepladders.add(stepladder)
    }

    override fun registerRecipes() {
        for (stepladder in registeredStepladders) {
            stepladder.registerRecipes()
        }
    }
}
