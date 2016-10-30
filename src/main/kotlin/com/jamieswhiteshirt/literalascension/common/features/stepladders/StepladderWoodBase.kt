package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraft.block.Block
import net.minecraft.block.BlockPlanks
import net.minecraft.block.material.Material
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

abstract class StepladderWoodBase(type: BlockPlanks.EnumType, logBlock: Block, logMetadata: Int, parent: Stepladders) : Stepladder(Material.WOOD, type.getName(), type.unlocalizedName, parent) {
    val feetIngredient = ItemStack(logBlock, 1, logMetadata)
    val stepsIngredient = ItemStack(Blocks.PLANKS, 1, type.metadata)

    override fun registerRecipes() {
        CraftingManager.getInstance().addRecipe(
                ItemStack(item),
                "F F",
                "FSF",
                "F F",
                'F', feetIngredient, 'S', stepsIngredient
        )
    }
}
