package com.jamieswhiteshirt.literalascension.common.features.carving

import com.jamieswhiteshirt.literalascension.common.SubFeature
import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.interop.tconstruct.ITConstructInterop
import net.minecraft.init.Items

class TConstructCarving(val interop: ITConstructInterop, override val parent: Carving) : SubFeature("tconstruct_carving", parent) {
    override fun register() {
        interop.registerCarvingModifier()
        val diamondCarvingTool = parent.CARVING_TOOLS.DIAMOND
        if (diamondCarvingTool != null) {
            interop.registerCarvingModifierItem(Items.APPLE)
        }
    }

    override fun registerRecipes() {}
}
