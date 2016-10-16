package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

object LARecipes {
    private fun registerStepladderRecipe(stepladder: EnumStepladder) {
        CraftingManager.getInstance().addRecipe(
                ItemStack(stepladder.item),
                "F F",
                "FSF",
                "F F",
                'F', stepladder.feetItem, 'S', stepladder.stepsItem
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
        for (stepladder in EnumStepladder.values()) {
            registerStepladderRecipe(stepladder)
        }

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
