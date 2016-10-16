package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import com.jamieswhiteshirt.literalascension.common.init.LAItems
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.block.BlockPlanks
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class EnumStepladder(
        val material: Material,
        val feetItem: ItemStack,
        val stepsItem: ItemStack
) {
    OAK(
            Material.WOOD,
            ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.OAK.metadata),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.OAK.metadata)
    ),
    SPRUCE(
            Material.WOOD,
            ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.SPRUCE.metadata),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.SPRUCE.metadata)
    ),
    BIRCH(
            Material.WOOD,
            ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.BIRCH.metadata),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.BIRCH.metadata)
    ),
    JUNGLE(
            Material.WOOD,
            ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.JUNGLE.metadata),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.JUNGLE.metadata)
    ),
    ACACIA(
            Material.WOOD,
            ItemStack(Blocks.LOG2, 1, BlockPlanks.EnumType.ACACIA.metadata - 4),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.ACACIA.metadata)
    ),
    DARK_OAK(
            Material.WOOD,
            ItemStack(Blocks.LOG2, 1, BlockPlanks.EnumType.DARK_OAK.metadata - 4),
            ItemStack(Blocks.PLANKS, 1, BlockPlanks.EnumType.DARK_OAK.metadata)
    ),
    IRON(
            Material.IRON,
            ItemStack(Items.IRON_INGOT),
            ItemStack(Items.IRON_INGOT)
    );

    val block = BlockStepladder(this).setHardness(0.5F).setUnlocalizedName("literalascension.stepladder.$this") as BlockStepladder
    val item = ItemStepladder(this).setUnlocalizedName("literalascension.stepladder.$this") as ItemStepladder

    override fun toString(): String = name.toLowerCase()
}
