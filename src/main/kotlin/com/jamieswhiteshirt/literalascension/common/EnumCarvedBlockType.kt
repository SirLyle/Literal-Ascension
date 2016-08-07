package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import net.minecraft.block.*
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.IStringSerializable

enum class EnumCarvedBlockType(val modelState: IBlockState, val material: EnumMaterial, val chuteBlock: () -> BlockChute, val notchedBlock: () -> BlockNotched) : IStringSerializable {
    OAK(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK), EnumMaterial.LOG_OLD, {LABlocks.OAK_CHUTE}, {LABlocks.OAK_NOTCHED}),
    SPRUCE(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), EnumMaterial.LOG_OLD, {LABlocks.SPRUCE_CHUTE}, {LABlocks.SPRUCE_NOTCHED}),
    BIRCH(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH), EnumMaterial.LOG_OLD, {LABlocks.BIRCH_CHUTE}, {LABlocks.BIRCH_NOTCHED}),
    JUNGLE(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), EnumMaterial.LOG_OLD, {LABlocks.JUNGLE_CHUTE}, {LABlocks.JUNGLE_NOTCHED}),
    ACACIA(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA), EnumMaterial.LOG_NEW, {LABlocks.ACACIA_CHUTE}, {LABlocks.ACACIA_NOTCHED}),
    DARK_OAK(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK), EnumMaterial.LOG_NEW, {LABlocks.DARK_OAK_CHUTE}, {LABlocks.DARK_OAK_NOTCHED}),
    STONE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), EnumMaterial.STONE, {LABlocks.STONE_CHUTE}, {LABlocks.STONE_NOTCHED}),
    GRANITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), EnumMaterial.STONE, {LABlocks.GRANITE_CHUTE}, {LABlocks.GRANITE_NOTCHED}),
    DIORITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), EnumMaterial.STONE, {LABlocks.DIORITE_CHUTE}, {LABlocks.DIORITE_NOTCHED}),
    ANDESITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), EnumMaterial.STONE, {LABlocks.ANDESITE_CHUTE}, {LABlocks.ANDESITE_NOTCHED});

    override fun getName(): String = name.toLowerCase()

    override fun toString(): String = name.toLowerCase()

    companion object {
        val fromOldLogType = mapOf(
                BlockPlanks.EnumType.OAK to OAK,
                BlockPlanks.EnumType.SPRUCE to SPRUCE,
                BlockPlanks.EnumType.BIRCH to BIRCH,
                BlockPlanks.EnumType.JUNGLE to JUNGLE
        )

        val fromNewLogType = mapOf(
                BlockPlanks.EnumType.ACACIA to ACACIA,
                BlockPlanks.EnumType.DARK_OAK to DARK_OAK
        )

        val fromStoneType = mapOf(
                BlockStone.EnumType.STONE to STONE,
                BlockStone.EnumType.GRANITE to GRANITE,
                BlockStone.EnumType.DIORITE to DIORITE,
                BlockStone.EnumType.ANDESITE to ANDESITE
        )
    }

    enum class EnumMaterial(val modelBlock: Block, val toolLevel: Int, val hardness: Float) {
        LOG_OLD(Blocks.LOG, 0, 2.0F),
        LOG_NEW(Blocks.LOG2, 0, 2.0F),
        STONE(Blocks.STONE, 3, 1.5F)
    }
}