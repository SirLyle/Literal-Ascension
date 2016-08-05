package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import net.minecraftforge.fml.common.registry.GameRegistry

object LABlocks {
    val OAK_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.OAK_STEPLADDER }).setUnlocalizedName("literalascension:stepladderOak") as BlockStepladder
    val SPRUCE_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.SPRUCE_STEPLADDER }).setUnlocalizedName("literalascension:stepladderSpruce") as BlockStepladder
    val BIRCH_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.BIRCH_STEPLADDER }).setUnlocalizedName("literalascension:stepladderBirch") as BlockStepladder
    val JUNGLE_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.JUNGLE_STEPLADDER }).setUnlocalizedName("literalascension:stepladderJungle") as BlockStepladder
    val ACACIA_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.ACACIA_STEPLADDER }).setUnlocalizedName("literalascension:stepladderAcacia") as BlockStepladder
    val DARK_OAK_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.DARK_OAK_STEPLADDER }).setUnlocalizedName("literalascension:stepladderDarkOak") as BlockStepladder
    val IRON_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.IRON_STEPLADDER }).setUnlocalizedName("literalascension:stepladderIron") as BlockStepladder

    fun register() {
        GameRegistry.register(OAK_STEPLADDER.setRegistryName("literalascension", "oak_stepladder"))
        GameRegistry.register(SPRUCE_STEPLADDER.setRegistryName("literalascension", "spruce_stepladder"))
        GameRegistry.register(BIRCH_STEPLADDER.setRegistryName("literalascension", "birch_stepladder"))
        GameRegistry.register(JUNGLE_STEPLADDER.setRegistryName("literalascension", "jungle_stepladder"))
        GameRegistry.register(ACACIA_STEPLADDER.setRegistryName("literalascension", "acacia_stepladder"))
        GameRegistry.register(DARK_OAK_STEPLADDER.setRegistryName("literalascension", "dark_oak_stepladder"))
        GameRegistry.register(IRON_STEPLADDER.setRegistryName("literalascension", "iron_stepladder"))
    }
}
