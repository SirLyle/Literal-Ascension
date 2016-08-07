package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.EnumCarvedBlockType
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import net.minecraftforge.fml.common.registry.GameRegistry

object LABlocks {
    val OAK_STEPLADDER = BlockStepladder({ LAItems.OAK_STEPLADDER }).setUnlocalizedName("literalascension.stepladderOak") as BlockStepladder
    val SPRUCE_STEPLADDER = BlockStepladder({ LAItems.SPRUCE_STEPLADDER }).setUnlocalizedName("literalascension.stepladderSpruce") as BlockStepladder
    val BIRCH_STEPLADDER = BlockStepladder({ LAItems.BIRCH_STEPLADDER }).setUnlocalizedName("literalascension.stepladderBirch") as BlockStepladder
    val JUNGLE_STEPLADDER = BlockStepladder({ LAItems.JUNGLE_STEPLADDER }).setUnlocalizedName("literalascension.stepladderJungle") as BlockStepladder
    val ACACIA_STEPLADDER = BlockStepladder({ LAItems.ACACIA_STEPLADDER }).setUnlocalizedName("literalascension.stepladderAcacia") as BlockStepladder
    val DARK_OAK_STEPLADDER = BlockStepladder({ LAItems.DARK_OAK_STEPLADDER }).setUnlocalizedName("literalascension.stepladderDarkOak") as BlockStepladder
    val IRON_STEPLADDER = BlockStepladder({ LAItems.IRON_STEPLADDER }).setUnlocalizedName("literalascension.stepladderIron") as BlockStepladder
    val OAK_CHUTE = BlockChute(EnumCarvedBlockType.OAK).setUnlocalizedName("literalascension.chuteOak") as BlockChute
    val SPRUCE_CHUTE = BlockChute(EnumCarvedBlockType.SPRUCE).setUnlocalizedName("literalascension.chuteSpruce") as BlockChute
    val BIRCH_CHUTE = BlockChute(EnumCarvedBlockType.BIRCH).setUnlocalizedName("literalascension.chuteBirch") as BlockChute
    val JUNGLE_CHUTE = BlockChute(EnumCarvedBlockType.JUNGLE).setUnlocalizedName("literalascension.chuteJungle") as BlockChute
    val ACACIA_CHUTE = BlockChute(EnumCarvedBlockType.ACACIA).setUnlocalizedName("literalascension.chuteAcacia") as BlockChute
    val DARK_OAK_CHUTE = BlockChute(EnumCarvedBlockType.DARK_OAK).setUnlocalizedName("literalascension.chuteDarkOak") as BlockChute
    val STONE_CHUTE = BlockChute(EnumCarvedBlockType.STONE).setUnlocalizedName("literalascension.chuteStone") as BlockChute
    val GRANITE_CHUTE = BlockChute(EnumCarvedBlockType.GRANITE).setUnlocalizedName("literalascension.chuteGranite") as BlockChute
    val DIORITE_CHUTE = BlockChute(EnumCarvedBlockType.DIORITE).setUnlocalizedName("literalascension.chuteDiorite") as BlockChute
    val ANDESITE_CHUTE = BlockChute(EnumCarvedBlockType.ANDESITE).setUnlocalizedName("literalascension.chuteAndesite") as BlockChute
    val OAK_NOTCHED = BlockNotched(EnumCarvedBlockType.OAK).setUnlocalizedName("literalascension.notchedOak") as BlockNotched
    val SPRUCE_NOTCHED = BlockNotched(EnumCarvedBlockType.SPRUCE).setUnlocalizedName("literalascension.notchedSpruce") as BlockNotched
    val BIRCH_NOTCHED = BlockNotched(EnumCarvedBlockType.BIRCH).setUnlocalizedName("literalascension.notchedBirch") as BlockNotched
    val JUNGLE_NOTCHED = BlockNotched(EnumCarvedBlockType.JUNGLE).setUnlocalizedName("literalascension.notchedJungle") as BlockNotched
    val ACACIA_NOTCHED = BlockNotched(EnumCarvedBlockType.ACACIA).setUnlocalizedName("literalascension.notchedAcacia") as BlockNotched
    val DARK_OAK_NOTCHED = BlockNotched(EnumCarvedBlockType.DARK_OAK).setUnlocalizedName("literalascension.notchedDarkOak") as BlockNotched
    val STONE_NOTCHED = BlockNotched(EnumCarvedBlockType.STONE).setUnlocalizedName("literalascension.notchedStone") as BlockNotched
    val GRANITE_NOTCHED = BlockNotched(EnumCarvedBlockType.GRANITE).setUnlocalizedName("literalascension.notchedGranite") as BlockNotched
    val DIORITE_NOTCHED = BlockNotched(EnumCarvedBlockType.DIORITE).setUnlocalizedName("literalascension.notchedDiorite") as BlockNotched
    val ANDESITE_NOTCHED = BlockNotched(EnumCarvedBlockType.ANDESITE).setUnlocalizedName("literalascension.notchedAndesite") as BlockNotched

    fun register() {
        registerStepladder(OAK_STEPLADDER, "oak")
        registerStepladder(SPRUCE_STEPLADDER, "spruce")
        registerStepladder(BIRCH_STEPLADDER, "birch")
        registerStepladder(JUNGLE_STEPLADDER, "jungle")
        registerStepladder(ACACIA_STEPLADDER, "acacia")
        registerStepladder(DARK_OAK_STEPLADDER, "dark_oak")
        registerStepladder(IRON_STEPLADDER, "iron")

        registerChute(OAK_CHUTE)
        registerChute(SPRUCE_CHUTE)
        registerChute(BIRCH_CHUTE)
        registerChute(JUNGLE_CHUTE)
        registerChute(ACACIA_CHUTE)
        registerChute(DARK_OAK_CHUTE)
        registerChute(STONE_CHUTE)
        registerChute(GRANITE_CHUTE)
        registerChute(DIORITE_CHUTE)
        registerChute(ANDESITE_CHUTE)

        registerNotched(OAK_NOTCHED)
        registerNotched(SPRUCE_NOTCHED)
        registerNotched(BIRCH_NOTCHED)
        registerNotched(JUNGLE_NOTCHED)
        registerNotched(ACACIA_NOTCHED)
        registerNotched(DARK_OAK_NOTCHED)
        registerNotched(STONE_NOTCHED)
        registerNotched(GRANITE_NOTCHED)
        registerNotched(DIORITE_NOTCHED)
        registerNotched(ANDESITE_NOTCHED)
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
