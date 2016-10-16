package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

object LAItems {
    val WOOD_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.WOOD).setUnlocalizedName("literalascension.carvingToolWood") as ItemCarvingTool
    val STONE_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.STONE).setUnlocalizedName("literalascension.carvingToolStone") as ItemCarvingTool
    val IRON_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.IRON).setUnlocalizedName("literalascension.carvingToolIron") as ItemCarvingTool
    val DIAMOND_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.DIAMOND).setUnlocalizedName("literalascension.carvingToolDiamond") as ItemCarvingTool
    val GOLD_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.GOLD).setUnlocalizedName("literalascension.carvingToolGold") as ItemCarvingTool

    fun register() {
        registerCarvingTool(WOOD_CARVING_TOOL, "wood")
        registerCarvingTool(STONE_CARVING_TOOL, "stone")
        registerCarvingTool(IRON_CARVING_TOOL, "iron")
        registerCarvingTool(DIAMOND_CARVING_TOOL, "diamond")
        registerCarvingTool(GOLD_CARVING_TOOL, "gold")
    }

    fun registerCarvingTool(carvingTool: ItemCarvingTool, type: String) {
        carvingTool.creativeTab = CreativeTab

        GameRegistry.register(carvingTool.setRegistryName("literalascension", "${type}_carving_tool"))
    }
}
