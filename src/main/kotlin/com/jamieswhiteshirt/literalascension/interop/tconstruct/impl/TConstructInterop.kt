package com.jamieswhiteshirt.literalascension.interop.tconstruct.impl

import com.jamieswhiteshirt.literalascension.common.features.Carving
import com.jamieswhiteshirt.literalascension.interop.tconstruct.ITConstructInterop
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge
import slimeknights.tconstruct.library.TinkerRegistry

class TConstructInterop: ITConstructInterop {
    val carvingTrait = ModCarving()

    override fun registerCarvingModifierItem(modifierItem: Item) {
        carvingTrait.addItem(modifierItem)
    }

    override fun registerCarvingModifier() {
        TinkerRegistry.registerModifier(carvingTrait)
    }

    override fun registerEventHandlers(carving: Carving) {
        MinecraftForge.EVENT_BUS.register(CarvingEvents(carvingTrait, carving))
    }
}
