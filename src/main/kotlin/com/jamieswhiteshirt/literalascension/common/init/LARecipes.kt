package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

object LARecipes {
    private fun registerStepladderRecipe(ladderItem: ItemStepladder, feetItem: Any, stepsItem: Any) {
        CraftingManager.getInstance().addRecipe(
                ItemStack(ladderItem),
                "F F",
                "FPF",
                "F F",
                'F', feetItem, 'P', stepsItem
        )
    }

    private fun registerCarvingToolRecipe(carving_toolItem: ItemCarvingTool, materialItem: Any) {
        CraftingManager.getInstance().addRecipe(
                ItemStack(carving_toolItem),
                "  M",
                " M ",
                "S  ",
                'M', materialItem, 'S', Items.STICK
        )
    }

    fun register() {
        registerStepladderRecipe(LAItems.OAK_STEPLADDER, ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.OAK.metadata), ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.OAK.metadata))
        registerStepladderRecipe(LAItems.SPRUCE_STEPLADDER, ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.SPRUCE.metadata), ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.SPRUCE.metadata))
        registerStepladderRecipe(LAItems.BIRCH_STEPLADDER, ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.BIRCH.metadata), ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.BIRCH.metadata))
        registerStepladderRecipe(LAItems.JUNGLE_STEPLADDER, ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.JUNGLE.metadata), ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.JUNGLE.metadata))
        registerStepladderRecipe(LAItems.ACACIA_STEPLADDER, ItemStack(Blocks.LOG2, 1, BlockPlanks.EnumType.ACACIA.metadata - 4), ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.ACACIA.metadata))
        registerStepladderRecipe(LAItems.DARK_OAK_STEPLADDER, ItemStack(Blocks.LOG2, 1, BlockPlanks.EnumType.DARK_OAK.metadata - 4), ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.DARK_OAK.metadata))
        registerStepladderRecipe(LAItems.IRON_STEPLADDER, Items.IRON_INGOT, Items.IRON_INGOT)

        registerCarvingToolRecipe(LAItems.WOOD_CARVING_TOOL, ItemStack(Blocks.PLANKS))
        registerCarvingToolRecipe(LAItems.STONE_CARVING_TOOL, ItemStack(Blocks.COBBLESTONE))
        registerCarvingToolRecipe(LAItems.IRON_CARVING_TOOL, ItemStack(Items.IRON_INGOT))
        registerCarvingToolRecipe(LAItems.DIAMOND_CARVING_TOOL, ItemStack(Items.DIAMOND))
        registerCarvingToolRecipe(LAItems.GOLD_CARVING_TOOL, ItemStack(Items.GOLD_INGOT))

        CraftingManager.getInstance().addRecipe(
                ItemStack(LABlocks.CLIMBING_ROPE, 8),
                "  S",
                " S ",
                "S  ",
                'S', Items.STRING
        )
    }
}
