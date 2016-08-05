package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.block.BlockChuteExit
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import net.minecraft.block.BlockPlanks
import net.minecraft.block.BlockStone

enum class ChuteType(val chute: BlockChute, val chuteExit: BlockChuteExit) {
    OAK(LABlocks.OAK_CHUTE, LABlocks.OAK_CHUTE_EXIT),
    SPRUCE(LABlocks.SPRUCE_CHUTE, LABlocks.SPRUCE_CHUTE_EXIT),
    BIRCH(LABlocks.BIRCH_CHUTE, LABlocks.BIRCH_CHUTE_EXIT),
    JUNGLE(LABlocks.JUNGLE_CHUTE, LABlocks.JUNGLE_CHUTE_EXIT),
    ACACIA(LABlocks.ACACIA_CHUTE, LABlocks.ACACIA_CHUTE_EXIT),
    DARK_OAK(LABlocks.DARK_OAK_CHUTE, LABlocks.DARK_OAK_CHUTE_EXIT),
    STONE(LABlocks.STONE_CHUTE, LABlocks.STONE_CHUTE_EXIT),
    GRANITE(LABlocks.GRANITE_CHUTE, LABlocks.GRANITE_CHUTE_EXIT),
    DIORITE(LABlocks.DIORITE_CHUTE, LABlocks.DIORITE_CHUTE_EXIT),
    ANDESITE(LABlocks.ANDESITE_CHUTE, LABlocks.ANDESITE_CHUTE_EXIT);

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
}