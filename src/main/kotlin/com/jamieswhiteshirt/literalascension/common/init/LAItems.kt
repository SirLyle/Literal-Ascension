package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

object LAItems {
    val OAK_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.OAK_STEPLADDER }).setUnlocalizedName("literalascension.stepladderOak") as ItemStepladder
    val SPRUCE_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.SPRUCE_STEPLADDER }).setUnlocalizedName("literalascension.stepladderSpruce") as ItemStepladder
    val BIRCH_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.BIRCH_STEPLADDER }).setUnlocalizedName("literalascension.stepladderBirch") as ItemStepladder
    val JUNGLE_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.JUNGLE_STEPLADDER }).setUnlocalizedName("literalascension.stepladderJungle") as ItemStepladder
    val ACACIA_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.ACACIA_STEPLADDER }).setUnlocalizedName("literalascension.stepladderAcacia") as ItemStepladder
    val DARK_OAK_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.DARK_OAK_STEPLADDER }).setUnlocalizedName("literalascension.stepladderDarkOak") as ItemStepladder
    val IRON_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.IRON_STEPLADDER }).setUnlocalizedName("literalascension.stepladderIron") as ItemStepladder

    val WOOD_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.WOOD).setUnlocalizedName("literalascension.carvingToolWood") as ItemCarvingTool
    val STONE_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.STONE).setUnlocalizedName("literalascension.carvingToolStone") as ItemCarvingTool
    val IRON_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.IRON).setUnlocalizedName("literalascension.carvingToolIron") as ItemCarvingTool
    val DIAMOND_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.DIAMOND).setUnlocalizedName("literalascension.carvingToolDiamond") as ItemCarvingTool
    val GOLD_CARVING_TOOL: ItemCarvingTool = ItemCarvingTool(Item.ToolMaterial.GOLD).setUnlocalizedName("literalascension.carvingToolGold") as ItemCarvingTool

    fun register() {
        registerStepladder(OAK_STEPLADDER, "oak")
        registerStepladder(SPRUCE_STEPLADDER, "spruce")
        registerStepladder(BIRCH_STEPLADDER, "birch")
        registerStepladder(JUNGLE_STEPLADDER, "jungle")
        registerStepladder(ACACIA_STEPLADDER, "acacia")
        registerStepladder(DARK_OAK_STEPLADDER, "dark_oak")
        registerStepladder(IRON_STEPLADDER, "iron")

        registerHollower(WOOD_CARVING_TOOL, "wood")
        registerHollower(STONE_CARVING_TOOL, "stone")
        registerHollower(IRON_CARVING_TOOL, "iron")
        registerHollower(DIAMOND_CARVING_TOOL, "diamond")
        registerHollower(GOLD_CARVING_TOOL, "gold")
    }

    fun registerStepladder(stepladder: ItemStepladder, type: String) {
        stepladder.creativeTab = CreativeTab

        GameRegistry.register(stepladder.setRegistryName("literalascension", "${type}_stepladder"))
    }

    fun registerHollower(carvingTool: ItemCarvingTool, type: String) {
        carvingTool.creativeTab = CreativeTab

        GameRegistry.register(carvingTool.setRegistryName("literalascension", "${type}_carving_tool"))
    }
}
