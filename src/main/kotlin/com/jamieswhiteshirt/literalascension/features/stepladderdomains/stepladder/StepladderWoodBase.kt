package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import net.minecraft.block.material.Material
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.oredict.ShapedOreRecipe

abstract class StepladderWoodBase(name: String, parent: StepladderDomain) : Stepladder(Material.WOOD, name, parent) {
    abstract val feetIngredient: Any
    abstract val stepsIngredient: Any

    override val flammability: Int get() = 20
    override val fireSpreadSpeed: Int get() = 5

    override fun registerRecipes() {
        CraftingManager.getInstance().addRecipe(ShapedOreRecipe(
                ItemStack(item),
                "F F",
                "FSF",
                "F F",
                'F', feetIngredient, 'S', stepsIngredient
        ))
    }
}
