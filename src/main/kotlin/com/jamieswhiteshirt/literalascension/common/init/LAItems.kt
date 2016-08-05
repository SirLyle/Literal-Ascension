package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraftforge.fml.common.registry.GameRegistry

object LAItems {
    val OAK_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.OAK_STEPLADDER }).setUnlocalizedName("literalascension:stepladderOak") as ItemStepladder
    val SPRUCE_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.SPRUCE_STEPLADDER }).setUnlocalizedName("literalascension:stepladderSpruce") as ItemStepladder
    val BIRCH_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.BIRCH_STEPLADDER }).setUnlocalizedName("literalascension:stepladderBirch") as ItemStepladder
    val JUNGLE_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.JUNGLE_STEPLADDER }).setUnlocalizedName("literalascension:stepladderJungle") as ItemStepladder
    val ACACIA_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.ACACIA_STEPLADDER }).setUnlocalizedName("literalascension:stepladderAcacia") as ItemStepladder
    val DARK_OAK_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.DARK_OAK_STEPLADDER }).setUnlocalizedName("literalascension:stepladderDarkOak") as ItemStepladder
    val IRON_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.IRON_STEPLADDER }).setUnlocalizedName("literalascension:stepladderIron") as ItemStepladder

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
