package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import net.minecraft.block.Block
import net.minecraft.block.BlockPlanks
import net.minecraft.block.BlockStone
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.init.Blocks

enum class CarvedBlockType(val sourceBlock: Block, val metadata: Int, val material: CarvedBlockMaterial, val typeName: String) {
    OAK(Blocks.LOG, BlockPlanks.EnumType.OAK.metadata, CarvedBlockMaterial.WOOD, "oak") {
        override val chuteBlock: BlockChute get() = LABlocks.OAK_CHUTE
    },
    SPRUCE(Blocks.LOG, BlockPlanks.EnumType.SPRUCE.metadata, CarvedBlockMaterial.WOOD, "spruce") {
        override val chuteBlock: BlockChute get() = LABlocks.SPRUCE_CHUTE
    },
    BIRCH(Blocks.LOG, BlockPlanks.EnumType.BIRCH.metadata, CarvedBlockMaterial.WOOD, "birch") {
        override val chuteBlock: BlockChute get() = LABlocks.BIRCH_CHUTE
    },
    JUNGLE(Blocks.LOG, BlockPlanks.EnumType.JUNGLE.metadata, CarvedBlockMaterial.WOOD, "jungle") {
        override val chuteBlock: BlockChute get() = LABlocks.JUNGLE_CHUTE
    },
    ACACIA(Blocks.LOG2, BlockPlanks.EnumType.ACACIA.metadata - 4, CarvedBlockMaterial.WOOD, "acacia") {
        override val chuteBlock: BlockChute get() = LABlocks.ACACIA_CHUTE
    },
    DARK_OAK(Blocks.LOG2, BlockPlanks.EnumType.DARK_OAK.metadata - 4, CarvedBlockMaterial.WOOD, "dark_oak") {
        override val chuteBlock: BlockChute get() = LABlocks.DARK_OAK_CHUTE
    },
    STONE(Blocks.STONE, BlockStone.EnumType.STONE.metadata, CarvedBlockMaterial.STONE, "stone") {
        override val chuteBlock: BlockChute get() = LABlocks.STONE_CHUTE
    },
    GRANITE(Blocks.STONE, BlockStone.EnumType.GRANITE.metadata, CarvedBlockMaterial.STONE, "granite") {
        override val chuteBlock: BlockChute get() = LABlocks.GRANITE_CHUTE
    },
    DIORITE(Blocks.STONE, BlockStone.EnumType.DIORITE.metadata, CarvedBlockMaterial.STONE, "diorite") {
        override val chuteBlock: BlockChute get() = LABlocks.DIORITE_CHUTE
    },
    ANDESITE(Blocks.STONE, BlockStone.EnumType.ANDESITE.metadata, CarvedBlockMaterial.STONE, "andesite") {
        override val chuteBlock: BlockChute get() = LABlocks.ANDESITE_CHUTE
    };

    abstract val chuteBlock: BlockChute

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