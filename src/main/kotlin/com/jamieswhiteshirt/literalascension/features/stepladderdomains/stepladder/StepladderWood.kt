package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

open class StepladderWood(logModelState: IBlockState, plankModelState: IBlockState, name: String, parent: StepladderDomain) : Stepladder(
        plankModelState,
        name,
        parent
) {
    val feetIngredient = ItemStack(logModelState.block, 1, logModelState.block.damageDropped(logModelState))
    val stepsIngredient = ItemStack(plankModelState.block, 1, plankModelState.block.damageDropped(plankModelState))

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
