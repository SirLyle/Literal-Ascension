package com.jamieswhiteshirt.literalascension.interop.tconstruct

import com.jamieswhiteshirt.literalascension.common.features.Carving
import net.minecraft.item.Item

interface ITConstructInterop {
    fun registerCarvingModifierItem(modifierItem: Item)

    fun registerCarvingModifier()

    fun registerEventHandlers(carving: Carving)
}
