package com.jamieswhiteshirt.literalascension.client

import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.block.BlockChuteBase
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import com.jamieswhiteshirt.literalascension.common.init.LAItems
import com.jamieswhiteshirt.literalascension.common.item.ItemChuteCarver
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemModelMesher
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item

class ClientProxy : CommonProxy() {
    override fun registerRenderers() {
        val itemModelMesher = Minecraft.getMinecraft().renderItem.itemModelMesher

        registerStepladderModel(itemModelMesher, LAItems.OAK_STEPLADDER, "oak")
        registerStepladderModel(itemModelMesher, LAItems.SPRUCE_STEPLADDER, "spruce")
        registerStepladderModel(itemModelMesher, LAItems.BIRCH_STEPLADDER, "birch")
        registerStepladderModel(itemModelMesher, LAItems.JUNGLE_STEPLADDER, "jungle")
        registerStepladderModel(itemModelMesher, LAItems.ACACIA_STEPLADDER, "acacia")
        registerStepladderModel(itemModelMesher, LAItems.DARK_OAK_STEPLADDER, "dark_oak")
        registerStepladderModel(itemModelMesher, LAItems.IRON_STEPLADDER, "iron")

        registerChuteModel(itemModelMesher, LABlocks.OAK_CHUTE, LABlocks.OAK_CHUTE_EXIT, "oak")
        registerChuteModel(itemModelMesher, LABlocks.SPRUCE_CHUTE, LABlocks.SPRUCE_CHUTE_EXIT, "spruce")
        registerChuteModel(itemModelMesher, LABlocks.BIRCH_CHUTE, LABlocks.BIRCH_CHUTE_EXIT, "birch")
        registerChuteModel(itemModelMesher, LABlocks.JUNGLE_CHUTE, LABlocks.JUNGLE_CHUTE_EXIT, "jungle")
        registerChuteModel(itemModelMesher, LABlocks.ACACIA_CHUTE, LABlocks.ACACIA_CHUTE_EXIT, "acacia")
        registerChuteModel(itemModelMesher, LABlocks.DARK_OAK_CHUTE, LABlocks.DARK_OAK_CHUTE_EXIT, "dark_oak")
        registerChuteModel(itemModelMesher, LABlocks.STONE_CHUTE, LABlocks.STONE_CHUTE_EXIT, "stone")
        registerChuteModel(itemModelMesher, LABlocks.GRANITE_CHUTE, LABlocks.GRANITE_CHUTE_EXIT, "granite")
        registerChuteModel(itemModelMesher, LABlocks.DIORITE_CHUTE, LABlocks.DIORITE_CHUTE_EXIT, "diorite")
        registerChuteModel(itemModelMesher, LABlocks.ANDESITE_CHUTE, LABlocks.ANDESITE_CHUTE_EXIT, "andesite")

        registerChuteCarverModel(itemModelMesher, LAItems.WOOD_CHUTE_CARVER, "wood")
        registerChuteCarverModel(itemModelMesher, LAItems.STONE_CHUTE_CARVER, "stone")
        registerChuteCarverModel(itemModelMesher, LAItems.IRON_CHUTE_CARVER, "iron")
        registerChuteCarverModel(itemModelMesher, LAItems.DIAMOND_CHUTE_CARVER, "diamond")
        registerChuteCarverModel(itemModelMesher, LAItems.GOLD_CHUTE_CARVER, "gold")
    }

    private fun registerStepladderModel(itemModelMesher: ItemModelMesher, stepladder: ItemStepladder, type: String) {
        itemModelMesher.register(stepladder, 0, ModelResourceLocation("literalascension:${type}_stepladder", "inventory"))
    }

    private fun registerChuteModel(itemModelMesher: ItemModelMesher, chute: BlockChuteBase, chuteExit: BlockChuteBase, type: String) {
        itemModelMesher.register(Item.getItemFromBlock(chute), 0, ModelResourceLocation("literalascension:${type}_chute", "inventory"))
        itemModelMesher.register(Item.getItemFromBlock(chuteExit), 0, ModelResourceLocation("literalascension:${type}_chute_exit", "inventory"))
    }

    private fun registerChuteCarverModel(itemModelMesher: ItemModelMesher, chuteCarver: ItemChuteCarver, type: String) {
        itemModelMesher.register(chuteCarver, 0, ModelResourceLocation("literalascension:${type}_chute_carver", "inventory"))
    }
}
