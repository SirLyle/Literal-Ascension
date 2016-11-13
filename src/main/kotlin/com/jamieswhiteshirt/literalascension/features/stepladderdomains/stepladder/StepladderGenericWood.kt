package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomainMiscellaneous
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.oredict.ShapedOreRecipe

class StepladderGenericWood(parent: StepladderDomainMiscellaneous) : Stepladder(
        Blocks.PLANKS.defaultState,
        "wood",
        parent
) {
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
