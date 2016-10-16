package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.carvedblock.CarvedBlock
import com.jamieswhiteshirt.literalascension.common.carvedblock.CarvedBlockMaterial
import net.minecraft.block.BlockNewLog
import net.minecraft.block.BlockOldLog
import net.minecraft.block.BlockPlanks
import net.minecraft.block.BlockStone
import net.minecraft.init.Blocks
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.*

object CarvedBlocks : Module {
    val MATERIAL_LOG = CarvedBlockMaterial(
            2.0F,
            LiteralAscension.config.woodRequiredCarvingToolLevel,
            LiteralAscension.config.woodSuitableCarvingToolLevel,
            LiteralAscension.config.woodSuitableToolDamageMultiplier,
            LiteralAscension.config.woodUnsuitableToolDamageMultiplier
    )
    val MATERIAL_STONE = CarvedBlockMaterial(
            1.5F,
            LiteralAscension.config.stoneRequiredCarvingToolLevel,
            LiteralAscension.config.stoneSuitableCarvingToolLevel,
            LiteralAscension.config.stoneSuitableToolDamageMultiplier,
            LiteralAscension.config.stoneUnsuitableToolDamageMultiplier
    )

    val LOG_OAK = CarvedBlock(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK), MATERIAL_LOG, "log_oak")
    val LOG_SPRUCE = CarvedBlock(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE), MATERIAL_LOG, "log_spruce")
    val LOG_BIRCH = CarvedBlock(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH), MATERIAL_LOG, "log_birch")
    val LOG_JUNGLE = CarvedBlock(Blocks.LOG.defaultState.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE), MATERIAL_LOG, "log_jungle")
    val LOG_ACACIA = CarvedBlock(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA), MATERIAL_LOG, "log_acacia")
    val LOG_DARK_OAK = CarvedBlock(Blocks.LOG2.defaultState.withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK), MATERIAL_LOG, "log_dark_oak")
    val STONE = CarvedBlock(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE), MATERIAL_STONE, "stone")
    val GRANITE = CarvedBlock(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), MATERIAL_STONE, "granite")
    val DIORITE = CarvedBlock(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), MATERIAL_STONE, "diorite")
    val ANDESITE = CarvedBlock(Blocks.STONE.defaultState.withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), MATERIAL_STONE, "andesite")

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

    private val registeredCarvedBlocks = ArrayList<CarvedBlock>()

    fun getRegisteredCarvedBlocks(): List<CarvedBlock> = registeredCarvedBlocks

    override fun register() {
        registerCarvedBlock(LOG_OAK)
        registerCarvedBlock(LOG_SPRUCE)
        registerCarvedBlock(LOG_BIRCH)
        registerCarvedBlock(LOG_JUNGLE)
        registerCarvedBlock(LOG_ACACIA)
        registerCarvedBlock(LOG_DARK_OAK)
        registerCarvedBlock(STONE)
        registerCarvedBlock(GRANITE)
        registerCarvedBlock(DIORITE)
        registerCarvedBlock(ANDESITE)
    }

    private fun registerCarvedBlock(carvedBlock: CarvedBlock) {
        GameRegistry.register(carvedBlock.chute.setRegistryName("literalascension", "${carvedBlock.name}_chute"))
        GameRegistry.register(carvedBlock.notched.setRegistryName("literalascension", "${carvedBlock.name}_notched"))

        registeredCarvedBlocks.add(carvedBlock)
    }

    override fun registerRecipes() {
    }
}