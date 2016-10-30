package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraft.block.material.Material
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

class StepladderSimple(material: Material, name: String, val ingredient: Any, parent: Stepladders) : Stepladder(material, name, parent) {
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
