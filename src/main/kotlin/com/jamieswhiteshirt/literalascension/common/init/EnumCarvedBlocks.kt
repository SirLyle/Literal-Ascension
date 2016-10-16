package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.api.ICarveMaterial
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import net.minecraft.block.*
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.IStringSerializable

enum class EnumCarvedBlocks(
        val modelState: IBlockState,
        val material: EnumMaterial
) {
    LOG_OAK(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK), EnumMaterial.LOG),
    LOG_SPRUCE(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), EnumMaterial.LOG),
    LOG_BIRCH(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH), EnumMaterial.LOG),
    LOG_JUNGLE(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), EnumMaterial.LOG),
    LOG_ACACIA(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA), EnumMaterial.LOG),
    LOG_DARK_OAK(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK), EnumMaterial.LOG),
    STONE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), EnumMaterial.STONE),
    GRANITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), EnumMaterial.STONE),
    DIORITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), EnumMaterial.STONE),
    ANDESITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), EnumMaterial.STONE);

    val chute = BlockChute(this)
    val notched = BlockNotched(this)

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
            override val suitableCarvingToolLevel: Int get() = LiteralAscension.config.woodSuitableCarvingToolLevel
            override val suitableToolDamageMultiplier: Int get() = LiteralAscension.config.woodSuitableToolDamageMultiplier
            override val unsuitableToolDamageMultiplier: Int get() = LiteralAscension.config.woodUnsuitableToolDamageMultiplier
        },
        STONE(1.5F) {
            override val requiredCarvingToolLevel: Int get() = LiteralAscension.config.stoneRequiredCarvingToolLevel
            override val suitableCarvingToolLevel: Int get() = LiteralAscension.config.stoneSuitableCarvingToolLevel
            override val suitableToolDamageMultiplier: Int get() = LiteralAscension.config.stoneSuitableToolDamageMultiplier
            override val unsuitableToolDamageMultiplier: Int get() = LiteralAscension.config.stoneUnsuitableToolDamageMultiplier
        };
    }
}