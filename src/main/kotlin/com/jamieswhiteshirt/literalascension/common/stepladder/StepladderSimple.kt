package com.jamieswhiteshirt.literalascension.common.stepladder

import net.minecraft.block.material.Material
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

class StepladderSimple(material: Material, name: String, val ingredient: Any) : Stepladder(material, name) {
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
