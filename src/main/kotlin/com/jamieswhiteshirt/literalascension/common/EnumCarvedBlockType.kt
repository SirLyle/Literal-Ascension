package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.api.ICarveMaterial
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import net.minecraft.block.*
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.IStringSerializable

enum class EnumCarvedBlockType(
        val modelState: IBlockState,
        val material: EnumMaterial,
        val chuteBlock: () -> BlockChute,
        val notchedBlock: () -> BlockNotched
) : IStringSerializable {
    LOG_OAK(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK), EnumMaterial.LOG, {LABlocks.LOG_OAK_CHUTE}, {LABlocks.LOG_OAK_NOTCHED}),
    LOG_SPRUCE(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), EnumMaterial.LOG, {LABlocks.LOG_SPRUCE_CHUTE}, {LABlocks.LOG_SPRUCE_NOTCHED}),
    LOG_BIRCH(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH), EnumMaterial.LOG, {LABlocks.LOG_BIRCH_CHUTE}, {LABlocks.LOG_BIRCH_NOTCHED}),
    LOG_JUNGLE(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), EnumMaterial.LOG, {LABlocks.LOG_JUNGLE_CHUTE}, {LABlocks.LOG_JUNGLE_NOTCHED}),
    LOG_ACACIA(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA), EnumMaterial.LOG, {LABlocks.LOG_ACACIA_CHUTE}, {LABlocks.LOG_ACACIA_NOTCHED}),
    LOG_DARK_OAK(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK), EnumMaterial.LOG, {LABlocks.LOG_DARK_OAK_CHUTE}, {LABlocks.LOG_DARK_OAK_NOTCHED}),
    STONE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), EnumMaterial.STONE, {LABlocks.STONE_CHUTE}, {LABlocks.STONE_NOTCHED}),
    GRANITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), EnumMaterial.STONE, {LABlocks.GRANITE_CHUTE}, {LABlocks.GRANITE_NOTCHED}),
    DIORITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), EnumMaterial.STONE, {LABlocks.DIORITE_CHUTE}, {LABlocks.DIORITE_NOTCHED}),
    ANDESITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), EnumMaterial.STONE, {LABlocks.ANDESITE_CHUTE}, {LABlocks.ANDESITE_NOTCHED});

    override fun getName(): String = name.toLowerCase()

    override fun toString(): String = name.toLowerCase()

    companion object {
        val fromOldLogType = mapOf(
                BlockPlanks.EnumType.OAK to LOG_OAK,
                BlockPlanks.EnumType.SPRUCE to LOG_SPRUCE,
                BlockPlanks.EnumType.BIRCH to LOG_BIRCH,
                BlockPlanks.EnumType.JUNGLE to LOG_JUNGLE
        )

        val fromNewLogType = mapOf(
                BlockPlanks.EnumType.ACACIA to LOG_ACACIA,
                BlockPlanks.EnumType.DARK_OAK to LOG_DARK_OAK
        )

        val fromStoneType = mapOf(
                BlockStone.EnumType.STONE to STONE,
                BlockStone.EnumType.GRANITE to GRANITE,
                BlockStone.EnumType.DIORITE to DIORITE,
                BlockStone.EnumType.ANDESITE to ANDESITE
        )
    }

    enum class EnumMaterial(val hardness: Float) : ICarveMaterial {
        LOG(2.0F) {
            override val requiredCarvingToolLevel: Int get() = LiteralAscension.config.woodRequiredCarvingToolLevel
            override val viableCarvingToolLevel: Int get() = LiteralAscension.config.woodViableCarvingToolLevel
            override val viableToolDamageMultiplier: Int get() = LiteralAscension.config.woodViableToolDamageMultiplier
            override val unviableToolDamageMultiplier: Int get() = LiteralAscension.config.woodUnviableToolDamageMultiplier
        },
        STONE(1.5F) {
            override val requiredCarvingToolLevel: Int get() = LiteralAscension.config.stoneRequiredCarvingToolLevel
            override val viableCarvingToolLevel: Int get() = LiteralAscension.config.stoneViableCarvingToolLevel
            override val viableToolDamageMultiplier: Int get() = LiteralAscension.config.stoneViableToolDamageMultiplier
            override val unviableToolDamageMultiplier: Int get() = LiteralAscension.config.stoneUnviableToolDamageMultiplier
        };
    }
}