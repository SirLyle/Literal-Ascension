package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.item.ItemChuteCarver
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

object LAItems {
    val OAK_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.OAK_STEPLADDER }).setUnlocalizedName("literalascension:stepladderOak") as ItemStepladder
    val SPRUCE_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.SPRUCE_STEPLADDER }).setUnlocalizedName("literalascension:stepladderSpruce") as ItemStepladder
    val BIRCH_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.BIRCH_STEPLADDER }).setUnlocalizedName("literalascension:stepladderBirch") as ItemStepladder
    val JUNGLE_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.JUNGLE_STEPLADDER }).setUnlocalizedName("literalascension:stepladderJungle") as ItemStepladder
    val ACACIA_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.ACACIA_STEPLADDER }).setUnlocalizedName("literalascension:stepladderAcacia") as ItemStepladder
    val DARK_OAK_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.DARK_OAK_STEPLADDER }).setUnlocalizedName("literalascension:stepladderDarkOak") as ItemStepladder
    val IRON_STEPLADDER: ItemStepladder = ItemStepladder({ LABlocks.IRON_STEPLADDER }).setUnlocalizedName("literalascension:stepladderIron") as ItemStepladder

    val WOOD_CHUTE_CARVER: ItemChuteCarver = ItemChuteCarver(Item.ToolMaterial.WOOD).setUnlocalizedName("literalascension:chuteCarverWood") as ItemChuteCarver
    val STONE_CHUTE_CARVER: ItemChuteCarver = ItemChuteCarver(Item.ToolMaterial.STONE).setUnlocalizedName("literalascension:chuteCarverStone") as ItemChuteCarver
    val IRON_CHUTE_CARVER: ItemChuteCarver = ItemChuteCarver(Item.ToolMaterial.IRON).setUnlocalizedName("literalascension:chuteCarverIron") as ItemChuteCarver
    val DIAMOND_CHUTE_CARVER: ItemChuteCarver = ItemChuteCarver(Item.ToolMaterial.DIAMOND).setUnlocalizedName("literalascension:chuteCarverDiamond") as ItemChuteCarver
    val GOLD_CHUTE_CARVER: ItemChuteCarver = ItemChuteCarver(Item.ToolMaterial.GOLD).setUnlocalizedName("literalascension:chuteCarverGold") as ItemChuteCarver

    fun register() {
        registerStepladder(OAK_STEPLADDER, "oak")
        registerStepladder(SPRUCE_STEPLADDER, "spruce")
        registerStepladder(BIRCH_STEPLADDER, "birch")
        registerStepladder(JUNGLE_STEPLADDER, "jungle")
        registerStepladder(ACACIA_STEPLADDER, "acacia")
        registerStepladder(DARK_OAK_STEPLADDER, "dark_oak")
        registerStepladder(IRON_STEPLADDER, "iron")

        registerHollower(WOOD_CHUTE_CARVER, "wood")
        registerHollower(STONE_CHUTE_CARVER, "stone")
        registerHollower(IRON_CHUTE_CARVER, "iron")
        registerHollower(DIAMOND_CHUTE_CARVER, "diamond")
        registerHollower(GOLD_CHUTE_CARVER, "gold")
    }

    fun registerStepladder(stepladder: ItemStepladder, type: String) {
        stepladder.creativeTab = CreativeTab

        GameRegistry.register(stepladder.setRegistryName("literalascension", "${type}_stepladder"))
    }

    fun registerHollower(chuteCarver: ItemChuteCarver, type: String) {
        chuteCarver.creativeTab = CreativeTab

        GameRegistry.register(chuteCarver.setRegistryName("literalascension", "${type}_chute_carver"))
    }
}
