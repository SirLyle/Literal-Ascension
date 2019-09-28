package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import com.jamieswhiteshirt.literalascension.util.asStack
import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class StepladderWoodBase(name: String, parent: StepladderDomain) : Stepladder(Material.WOOD, name, parent) {
    abstract val feetIngredient: Any
    abstract val stepsIngredient: Any

    override val flammability: Int get() = 20
    override val fireSpreadSpeed: Int get() = 5

    override fun registerRecipes() {
        GameRegistry.addShapedRecipe(
                ResourceLocation("literalascension", "${name}_stepladder"),
                ResourceLocation("literalascension", "stepladder"),
                item.asStack(),
                "F F",
                "FSF",
                "F F",
                'F', feetIngredient, 'S', stepsIngredient

        )
    }
}
