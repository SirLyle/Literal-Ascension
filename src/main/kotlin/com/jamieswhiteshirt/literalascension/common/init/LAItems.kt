package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.EnumStepladderType
import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

object LAItems {
    val OAK_STEPLADDER: ItemStepladder = ItemStepladder(EnumStepladderType.OAK).setUnlocalizedName("literalascension.stepladder.oak") as ItemStepladder
    val SPRUCE_STEPLADDER: ItemStepladder = ItemStepladder(EnumStepladderType.SPRUCE).setUnlocalizedName("literalascension.stepladder.spruce") as ItemStepladder
    val BIRCH_STEPLADDER: ItemStepladder = ItemStepladder(EnumStepladderType.BIRCH).setUnlocalizedName("literalascension.stepladder.birch") as ItemStepladder
    val JUNGLE_STEPLADDER: ItemStepladder = ItemStepladder(EnumStepladderType.JUNGLE).setUnlocalizedName("literalascension.stepladder.jungle") as ItemStepladder
    val ACACIA_STEPLADDER: ItemStepladder = ItemStepladder(EnumStepladderType.ACACIA).setUnlocalizedName("literalascension.stepladder.acacia") as ItemStepladder
    val DARK_OAK_STEPLADDER: ItemStepladder = ItemStepladder(EnumStepladderType.DARK_OAK).setUnlocalizedName("literalascension.stepladder.big_oak") as ItemStepladder
    val IRON_STEPLADDER: ItemStepladder = ItemStepladder(EnumStepladderType.IRON).setUnlocalizedName("literalascension.stepladder.iron") as ItemStepladder

    val WOOD_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.WOOD).setUnlocalizedName("literalascension.carvingToolWood") as ItemCarvingTool
    val STONE_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.STONE).setUnlocalizedName("literalascension.carvingToolStone") as ItemCarvingTool
    val IRON_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.IRON).setUnlocalizedName("literalascension.carvingToolIron") as ItemCarvingTool
    val DIAMOND_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.DIAMOND).setUnlocalizedName("literalascension.carvingToolDiamond") as ItemCarvingTool
    val GOLD_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.GOLD).setUnlocalizedName("literalascension.carvingToolGold") as ItemCarvingTool

    fun register() {
        registerStepladder(OAK_STEPLADDER)
        registerStepladder(SPRUCE_STEPLADDER)
        registerStepladder(BIRCH_STEPLADDER)
        registerStepladder(JUNGLE_STEPLADDER)
        registerStepladder(ACACIA_STEPLADDER)
        registerStepladder(DARK_OAK_STEPLADDER)
        registerStepladder(IRON_STEPLADDER)

        registerCarvingTool(WOOD_CARVING_TOOL, "wood")
        registerCarvingTool(STONE_CARVING_TOOL, "stone")
        registerCarvingTool(IRON_CARVING_TOOL, "iron")
        registerCarvingTool(DIAMOND_CARVING_TOOL, "diamond")
        registerCarvingTool(GOLD_CARVING_TOOL, "gold")
    }

    fun registerStepladder(stepladder: ItemStepladder) {
        stepladder.creativeTab = CreativeTab

        GameRegistry.register(stepladder.setRegistryName("literalascension", "${stepladder.type.getName()}_stepladder"))
    }

    fun registerCarvingTool(carvingTool: ItemCarvingTool, type: String) {
        carvingTool.creativeTab = CreativeTab

        GameRegistry.register(carvingTool.setRegistryName("literalascension", "${type}_carving_tool"))
    }
}
