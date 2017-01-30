package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomainMiscellaneous
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.oredict.ShapedOreRecipe

class StepladderGenericWood(parent: StepladderDomainMiscellaneous) : StepladderWoodBase("wood", parent) {
    override val feetIngredient: Any = "logWood"
    override val stepsIngredient: Any = "plankWood"
}
