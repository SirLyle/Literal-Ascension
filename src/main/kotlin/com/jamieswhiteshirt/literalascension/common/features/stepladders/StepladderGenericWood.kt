package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import net.minecraft.block.material.Material
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.oredict.ShapedOreRecipe

class StepladderGenericWood(parent: Stepladders) : Stepladder(Material.WOOD, "wood", parent) {
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
