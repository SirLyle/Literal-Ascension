package com.jamieswhiteshirt.literalascension.features.carving

import com.jamieswhiteshirt.literalascension.SubFeatureCollection
import com.jamieswhiteshirt.literalascension.features.Carving
import com.jamieswhiteshirt.literalascension.features.carving.carvingtools.CarvingTool
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraftforge.common.config.Configuration

class CarvingTools(config: Configuration, override val parent: Carving) : SubFeatureCollection<CarvingTool>("tools", parent) {
    val WOOD    = optionalOn(config, CarvingTool(Blocks.PLANKS, Item.ToolMaterial.WOOD, "wood", this))
    val STONE   = optionalOn(config, CarvingTool(Blocks.COBBLESTONE, Item.ToolMaterial.STONE, "stone", this))
    val IRON    = optionalOn(config, CarvingTool(Items.IRON_INGOT, Item.ToolMaterial.IRON, "iron", this))
    val DIAMOND = optionalOn(config, CarvingTool(Items.DIAMOND, Item.ToolMaterial.DIAMOND, "diamond", this))
    val GOLD    = optionalOn(config, CarvingTool(Items.GOLD_INGOT, Item.ToolMaterial.GOLD, "gold", this))
}
