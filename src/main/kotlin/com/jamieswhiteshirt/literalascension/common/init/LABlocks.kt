package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.EnumCarvedBlockType
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import net.minecraft.item.ItemBlock
import net.minecraftforge.fml.common.registry.GameRegistry

object LABlocks {
    val OAK_STEPLADDER = BlockStepladder({ LAItems.OAK_STEPLADDER }).setUnlocalizedName("literalascension.stepladder.oak") as BlockStepladder
    val SPRUCE_STEPLADDER = BlockStepladder({ LAItems.SPRUCE_STEPLADDER }).setUnlocalizedName("literalascension.stepladder.spruce") as BlockStepladder
    val BIRCH_STEPLADDER = BlockStepladder({ LAItems.BIRCH_STEPLADDER }).setUnlocalizedName("literalascension.stepladder.birch") as BlockStepladder
    val JUNGLE_STEPLADDER = BlockStepladder({ LAItems.JUNGLE_STEPLADDER }).setUnlocalizedName("literalascension.stepladder.jungle") as BlockStepladder
    val ACACIA_STEPLADDER = BlockStepladder({ LAItems.ACACIA_STEPLADDER }).setUnlocalizedName("literalascension.stepladder.acacia") as BlockStepladder
    val DARK_OAK_STEPLADDER = BlockStepladder({ LAItems.DARK_OAK_STEPLADDER }).setUnlocalizedName("literalascension.stepladder.big_oak") as BlockStepladder
    val IRON_STEPLADDER = BlockStepladder({ LAItems.IRON_STEPLADDER }).setUnlocalizedName("literalascension.stepladder.iron") as BlockStepladder

    val LOG_OAK_CHUTE = BlockChute(EnumCarvedBlockType.LOG_OAK)
    val LOG_SPRUCE_CHUTE = BlockChute(EnumCarvedBlockType.LOG_SPRUCE)
    val LOG_BIRCH_CHUTE = BlockChute(EnumCarvedBlockType.LOG_BIRCH)
    val LOG_JUNGLE_CHUTE = BlockChute(EnumCarvedBlockType.LOG_JUNGLE)
    val LOG_ACACIA_CHUTE = BlockChute(EnumCarvedBlockType.LOG_ACACIA)
    val LOG_DARK_OAK_CHUTE = BlockChute(EnumCarvedBlockType.LOG_DARK_OAK)
    val STONE_CHUTE = BlockChute(EnumCarvedBlockType.STONE)
    val GRANITE_CHUTE = BlockChute(EnumCarvedBlockType.GRANITE)
    val DIORITE_CHUTE = BlockChute(EnumCarvedBlockType.DIORITE)
    val ANDESITE_CHUTE = BlockChute(EnumCarvedBlockType.ANDESITE)

    val LOG_OAK_NOTCHED = BlockNotched(EnumCarvedBlockType.LOG_OAK)
    val LOG_SPRUCE_NOTCHED = BlockNotched(EnumCarvedBlockType.LOG_SPRUCE)
    val LOG_BIRCH_NOTCHED = BlockNotched(EnumCarvedBlockType.LOG_BIRCH)
    val LOG_JUNGLE_NOTCHED = BlockNotched(EnumCarvedBlockType.LOG_JUNGLE)
    val LOG_ACACIA_NOTCHED = BlockNotched(EnumCarvedBlockType.LOG_ACACIA)
    val LOG_DARK_OAK_NOTCHED = BlockNotched(EnumCarvedBlockType.LOG_DARK_OAK)
    val STONE_NOTCHED = BlockNotched(EnumCarvedBlockType.STONE)
    val GRANITE_NOTCHED = BlockNotched(EnumCarvedBlockType.GRANITE)
    val DIORITE_NOTCHED = BlockNotched(EnumCarvedBlockType.DIORITE)
    val ANDESITE_NOTCHED = BlockNotched(EnumCarvedBlockType.ANDESITE)

    val CLIMBING_ROPE = BlockClimbingRope().setUnlocalizedName("literalascension.climbingRope").setCreativeTab(CreativeTab) as BlockClimbingRope

    fun register() {
        registerStepladder(OAK_STEPLADDER, "oak")
        registerStepladder(SPRUCE_STEPLADDER, "spruce")
        registerStepladder(BIRCH_STEPLADDER, "birch")
        registerStepladder(JUNGLE_STEPLADDER, "jungle")
        registerStepladder(ACACIA_STEPLADDER, "acacia")
        registerStepladder(DARK_OAK_STEPLADDER, "dark_oak")
        registerStepladder(IRON_STEPLADDER, "iron")

        registerChute(LOG_OAK_CHUTE)
        registerChute(LOG_SPRUCE_CHUTE)
        registerChute(LOG_BIRCH_CHUTE)
        registerChute(LOG_JUNGLE_CHUTE)
        registerChute(LOG_ACACIA_CHUTE)
        registerChute(LOG_DARK_OAK_CHUTE)
        registerChute(STONE_CHUTE)
        registerChute(GRANITE_CHUTE)
        registerChute(DIORITE_CHUTE)
        registerChute(ANDESITE_CHUTE)

        registerNotched(LOG_OAK_NOTCHED)
        registerNotched(LOG_SPRUCE_NOTCHED)
        registerNotched(LOG_BIRCH_NOTCHED)
        registerNotched(LOG_JUNGLE_NOTCHED)
        registerNotched(LOG_ACACIA_NOTCHED)
        registerNotched(LOG_DARK_OAK_NOTCHED)
        registerNotched(STONE_NOTCHED)
        registerNotched(GRANITE_NOTCHED)
        registerNotched(DIORITE_NOTCHED)
        registerNotched(ANDESITE_NOTCHED)

        GameRegistry.register(CLIMBING_ROPE.setRegistryName("literalascension", "climbing_rope"))
        GameRegistry.register(ItemBlock(CLIMBING_ROPE).setRegistryName("literalascension", "climbing_rope"))
    }

    private fun registerStepladder(stepladder: BlockStepladder, type: String) {
        GameRegistry.register(stepladder.setRegistryName("literalascension", "${type}_stepladder"))
    }

    private fun registerChute(chute: BlockChute) {
        GameRegistry.register(chute.setRegistryName("literalascension", "${chute.type.getName()}_chute"))
    }

    private fun registerNotched(notched: BlockNotched) {
        GameRegistry.register(notched.setRegistryName("literalascension", "${notched.type.getName()}_notched"))
    }
}
