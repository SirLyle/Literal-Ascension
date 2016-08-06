package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CarvedBlockType
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
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
    val OAK_CHUTE = BlockChute(CarvedBlockType.OAK).setUnlocalizedName("literalascension.chuteOak") as BlockChute
    val SPRUCE_CHUTE = BlockChute(CarvedBlockType.SPRUCE).setUnlocalizedName("literalascension.chuteSpruce") as BlockChute
    val BIRCH_CHUTE = BlockChute(CarvedBlockType.BIRCH).setUnlocalizedName("literalascension.chuteBirch") as BlockChute
    val JUNGLE_CHUTE = BlockChute(CarvedBlockType.JUNGLE).setUnlocalizedName("literalascension.chuteJungle") as BlockChute
    val ACACIA_CHUTE = BlockChute(CarvedBlockType.ACACIA).setUnlocalizedName("literalascension.chuteAcacia") as BlockChute
    val DARK_OAK_CHUTE = BlockChute(CarvedBlockType.DARK_OAK).setUnlocalizedName("literalascension.chuteDarkOak") as BlockChute
    val STONE_CHUTE = BlockChute(CarvedBlockType.STONE).setUnlocalizedName("literalascension.chuteStone") as BlockChute
    val GRANITE_CHUTE = BlockChute(CarvedBlockType.GRANITE).setUnlocalizedName("literalascension.chuteGranite") as BlockChute
    val DIORITE_CHUTE = BlockChute(CarvedBlockType.DIORITE).setUnlocalizedName("literalascension.chuteDiorite") as BlockChute
    val ANDESITE_CHUTE = BlockChute(CarvedBlockType.ANDESITE).setUnlocalizedName("literalascension.chuteAndesite") as BlockChute

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
    }

    private fun registerStepladder(stepladder: BlockStepladder, type: String) {
        GameRegistry.register(stepladder.setRegistryName("literalascension", "${type}_stepladder"))
    }

    private fun registerChute(chute: BlockChute) {
        GameRegistry.register(chute.setRegistryName("literalascension", "${chute.type.typeName}_chute"))
    }
}
