package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import com.jamieswhiteshirt.literalascension.common.init.LAItems
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.block.BlockPlanks
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class EnumStepladderType(
        val modelState: IBlockState,
        val feetMaterial: ItemStack,
        val stepsMaterial: ItemStack,
        val block: () -> BlockStepladder,
        val item: () -> ItemStepladder
) : IStringSerializable {
    OAK(
            Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK),
            ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.OAK.metadata),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.OAK.metadata),
            {LABlocks.OAK_STEPLADDER},
            {LAItems.OAK_STEPLADDER}
    ),
    SPRUCE(
            Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE),
            ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.SPRUCE.metadata),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.SPRUCE.metadata),
            {LABlocks.SPRUCE_STEPLADDER},
            {LAItems.SPRUCE_STEPLADDER}
    ),
    BIRCH(
            Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH),
            ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.BIRCH.metadata),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.BIRCH.metadata),
            {LABlocks.BIRCH_STEPLADDER},
            {LAItems.BIRCH_STEPLADDER}
    ),
    JUNGLE(
            Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE),
            ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.JUNGLE.metadata),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.JUNGLE.metadata),
            {LABlocks.JUNGLE_STEPLADDER},
            {LAItems.JUNGLE_STEPLADDER}
    ),
    ACACIA(
            Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.ACACIA.metadata),
            ItemStack(Blocks.LOG2, 1, BlockPlanks.EnumType.ACACIA.metadata - 4),
            {LABlocks.ACACIA_STEPLADDER},
            {LAItems.ACACIA_STEPLADDER}
    ),
    DARK_OAK(
            Blocks.PLANKS.defaultState.withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK),
            ItemStack(Blocks.LOG2, 1, BlockPlanks.EnumType.DARK_OAK.metadata - 4),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.DARK_OAK.metadata),
            {LABlocks.DARK_OAK_STEPLADDER},
            {LAItems.DARK_OAK_STEPLADDER}
    ),
    IRON(
            Blocks.IRON_BLOCK.defaultState,
            ItemStack(Items.IRON_INGOT),
            ItemStack(Items.IRON_INGOT),
            {LABlocks.IRON_STEPLADDER},
            {LAItems.IRON_STEPLADDER}
    );

    override fun getName(): String = name.toLowerCase()

    override fun toString(): String = name.toLowerCase()
}
