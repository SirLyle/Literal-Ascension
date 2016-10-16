package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

object LARecipes {
    private fun registerStepladderRecipe(item: ItemStepladder) {
        CraftingManager.getInstance().addRecipe(
                ItemStack(item.type.item()),
                "F F",
                "FSF",
                "F F",
                'F', item.type.feetMaterial, 'S', item.type.stepsMaterial
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
        registerStepladderRecipe(LAItems.OAK_STEPLADDER)
        registerStepladderRecipe(LAItems.SPRUCE_STEPLADDER)
        registerStepladderRecipe(LAItems.BIRCH_STEPLADDER)
        registerStepladderRecipe(LAItems.JUNGLE_STEPLADDER)
        registerStepladderRecipe(LAItems.ACACIA_STEPLADDER)
        registerStepladderRecipe(LAItems.DARK_OAK_STEPLADDER)
        registerStepladderRecipe(LAItems.IRON_STEPLADDER)

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
