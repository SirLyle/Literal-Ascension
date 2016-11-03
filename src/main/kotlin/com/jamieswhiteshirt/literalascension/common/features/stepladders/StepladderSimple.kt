package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

class StepladderSimple(modelState: IBlockState, name: String, val ingredient: Any, parent: Stepladders) : Stepladder(modelState, name, parent) {
    override fun registerRecipes() {
        CraftingManager.getInstance().addRecipe(
                ItemStack(item),
                "I I",
                "III",
                "I I",
                'I', ingredient
        )
    }
}
