package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraft.block.BlockPlanks
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

abstract class StepladderWoodBase(logModelState: IBlockState, type: BlockPlanks.EnumType, parent: Stepladders) : Stepladder(
        Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, type),
        type.getName(),
        type.unlocalizedName,
        parent
) {
    val feetIngredient = ItemStack(logModelState.block, 1, logModelState.block.damageDropped(logModelState))
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
