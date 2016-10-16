package com.jamieswhiteshirt.literalascension.common.stepladder

import net.minecraft.block.material.Material
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.oredict.ShapedOreRecipe

class StepladderGenericWood() : Stepladder(Material.WOOD, "wood", "wood") {
    override fun registerRecipes() {
        CraftingManager.getInstance().addRecipe(ShapedOreRecipe(
                ItemStack(item),
                "F F",
                "FSF",
                "F F",
                'F', "logWood", 'S', "plankWood"
        ))
    }
}
