package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import com.jamieswhiteshirt.literalascension.util.asStack
import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

open class StepladderMaterial(
        val ingredient: Any,
        name: String,
        parent: StepladderDomain,
        override val fireSpreadSpeed: Int = 0,
        override val flammability: Int = 0
) : Stepladder(Material.IRON, name, parent) {
    override fun registerRecipes() {
        GameRegistry.addShapedRecipe(
                ResourceLocation("literalascension", "${name}_stepladder_material"),
                ResourceLocation("literalascension", "stepladder_material"),
                item.asStack(),
                "I I",
                "III",
                "I I",
                'I', ingredient

        )
    }
}
