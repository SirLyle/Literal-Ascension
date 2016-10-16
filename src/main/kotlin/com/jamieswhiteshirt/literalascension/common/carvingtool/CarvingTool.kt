package com.jamieswhiteshirt.literalascension.common.carvingtool

import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import net.minecraft.item.Item

class CarvingTool(val ingredient: Any, toolMaterial: Item.ToolMaterial, val name: String, val unlocalizedName: String) {
    val item: ItemCarvingTool = ItemCarvingTool(toolMaterial).setUnlocalizedName("literalascension.carvingTool.$unlocalizedName") as ItemCarvingTool

    constructor(ingredient: Any, toolMaterial: Item.ToolMaterial, name: String) : this(ingredient, toolMaterial, name, name)
}
