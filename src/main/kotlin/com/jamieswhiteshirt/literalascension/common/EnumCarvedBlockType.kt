package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import net.minecraft.block.*
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.util.IStringSerializable

enum class EnumCarvedBlockType(val modelState: IBlockState, val material: CarvedBlockMaterial, val chuteBlock: () -> BlockChute) : IStringSerializable {
    OAK(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK), CarvedBlockMaterial.WOOD, {LABlocks.OAK_CHUTE}),
    SPRUCE(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), CarvedBlockMaterial.WOOD, {LABlocks.SPRUCE_CHUTE}),
    BIRCH(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH), CarvedBlockMaterial.WOOD, {LABlocks.BIRCH_CHUTE}),
    JUNGLE(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), CarvedBlockMaterial.WOOD, {LABlocks.JUNGLE_CHUTE}),
    ACACIA(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA), CarvedBlockMaterial.WOOD, {LABlocks.ACACIA_CHUTE}),
    DARK_OAK(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA), CarvedBlockMaterial.WOOD, {LABlocks.DARK_OAK_CHUTE}),
    STONE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), CarvedBlockMaterial.STONE, {LABlocks.STONE_CHUTE}),
    GRANITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), CarvedBlockMaterial.STONE, {LABlocks.GRANITE_CHUTE}),
    DIORITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), CarvedBlockMaterial.STONE, {LABlocks.DIORITE_CHUTE}),
    ANDESITE(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), CarvedBlockMaterial.STONE, {LABlocks.ANDESITE_CHUTE});

    val modelBlock = modelState.block

    override fun getName(): String = name.toLowerCase()

    override fun toString(): String = name.toLowerCase()

    companion object {
        val fromPlankType = mapOf(
                BlockPlanks.EnumType.OAK to OAK,
                BlockPlanks.EnumType.SPRUCE to SPRUCE,
                BlockPlanks.EnumType.BIRCH to BIRCH,
                BlockPlanks.EnumType.JUNGLE to JUNGLE,
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

    enum class CarvedBlockMaterial(val blockMaterial: Material, val toolLevel: Int, val hardness: Float, val soundType: SoundType) {
        WOOD(Material.WOOD, 0, 2.0F, SoundType.WOOD),
        STONE(Material.ROCK, 3, 1.5F, SoundType.STONE)
    }
}