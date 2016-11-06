package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.StepladderDomain
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

class StepladderSimple(modelState: IBlockState, name: String, val ingredient: Any, parent: StepladderDomain) : Stepladder(modelState, name, parent) {
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
