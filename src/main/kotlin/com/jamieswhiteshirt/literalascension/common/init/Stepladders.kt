package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.stepladder.Stepladder
import com.jamieswhiteshirt.literalascension.common.stepladder.StepladderNewWood
import com.jamieswhiteshirt.literalascension.common.stepladder.StepladderOldWood
import com.jamieswhiteshirt.literalascension.common.stepladder.StepladderSimple
import net.minecraft.block.BlockPlanks
import net.minecraft.block.material.Material
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.*

object Stepladders {
    val OAK = StepladderOldWood(BlockPlanks.EnumType.OAK)
    val SPRUCE = StepladderOldWood(BlockPlanks.EnumType.SPRUCE)
    val BIRCH = StepladderOldWood(BlockPlanks.EnumType.BIRCH)
    val JUNGLE = StepladderOldWood(BlockPlanks.EnumType.JUNGLE)
    val ACACIA = StepladderNewWood(BlockPlanks.EnumType.ACACIA)
    val DARK_OAK = StepladderNewWood(BlockPlanks.EnumType.DARK_OAK)
    val IRON = StepladderSimple(Material.IRON, "iron", ItemStack(Items.IRON_INGOT))

    private val registeredStepladders = ArrayList<Stepladder>()

    fun register() {
        if (LiteralAscension.config.stepladderOakEnabled) registerStepladder(OAK)
        if (LiteralAscension.config.stepladderSpruceEnabled) registerStepladder(SPRUCE)
        if (LiteralAscension.config.stepladderBirchEnabled) registerStepladder(BIRCH)
        if (LiteralAscension.config.stepladderJungleEnabled) registerStepladder(JUNGLE)
        if (LiteralAscension.config.stepladderAcaciaEnabled) registerStepladder(ACACIA)
        if (LiteralAscension.config.stepladderDarkOakEnabled) registerStepladder(DARK_OAK)
        if (LiteralAscension.config.stepladderIronEnabled) registerStepladder(IRON)
    }

    private fun registerStepladder(stepladder: Stepladder) {
        stepladder.item.creativeTab = CreativeTab

        GameRegistry.register(stepladder.block.setRegistryName("literalascension", "${stepladder.name}_stepladder"))
        GameRegistry.register(stepladder.item.setRegistryName("literalascension", "${stepladder.name}_stepladder"))

        registeredStepladders.add(stepladder)
    }

    fun getRegisteredStepladders() : List<Stepladder> = registeredStepladders
}
