package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.item.ItemChuteCarver
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.block.BlockPlanks
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager

object LARecipes {
    private fun registerStepladderRecipe(ladderItem: ItemStepladder, feetItem: ItemStack) {
        CraftingManager.getInstance().addRecipe(
                ItemStack(ladderItem),
                "FSF",
                "FSF",
                "FSF",
                'F', feetItem, 'S', Items.STICK
        )
    }

    private fun registerChuteCarverRecipe(chuteCarverItem: ItemChuteCarver, materialItem: ItemStack) {
        CraftingManager.getInstance().addRecipe(
                ItemStack(chuteCarverItem),
                "  M",
                " M ",
                "S  ",
                'M', materialItem, 'S', Items.STICK
        )
    }

    fun register() {
        registerStepladderRecipe(LAItems.OAK_STEPLADDER, ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.OAK.metadata))
        registerStepladderRecipe(LAItems.SPRUCE_STEPLADDER, ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.SPRUCE.metadata))
        registerStepladderRecipe(LAItems.BIRCH_STEPLADDER, ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.BIRCH.metadata))
        registerStepladderRecipe(LAItems.JUNGLE_STEPLADDER, ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.JUNGLE.metadata))
        registerStepladderRecipe(LAItems.ACACIA_STEPLADDER, ItemStack(Blocks.LOG2, 1, BlockPlanks.EnumType.ACACIA.metadata - 4))
        registerStepladderRecipe(LAItems.DARK_OAK_STEPLADDER, ItemStack(Blocks.LOG2, 1, BlockPlanks.EnumType.DARK_OAK.metadata - 4))
        registerStepladderRecipe(LAItems.IRON_STEPLADDER, ItemStack(Items.IRON_INGOT))

        registerChuteCarverRecipe(LAItems.WOOD_CHUTE_CARVER, ItemStack(Blocks.PLANKS))
        registerChuteCarverRecipe(LAItems.STONE_CHUTE_CARVER, ItemStack(Blocks.COBBLESTONE))
        registerChuteCarverRecipe(LAItems.IRON_CHUTE_CARVER, ItemStack(Items.IRON_INGOT))
        registerChuteCarverRecipe(LAItems.DIAMOND_CHUTE_CARVER, ItemStack(Items.DIAMOND))
        registerChuteCarverRecipe(LAItems.GOLD_CHUTE_CARVER, ItemStack(Items.GOLD_INGOT))
    }
}
