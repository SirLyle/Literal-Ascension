package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

open class StepladderMaterial(
        val ingredient: Any,
        name: String,
        parent: StepladderDomain,
        override val fireSpreadSpeed: Int = 0,
        override val flammability: Int = 0
) : Stepladder(Material.IRON, name, parent) {
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
