package com.jamieswhiteshirt.literalascension.client

import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import com.jamieswhiteshirt.literalascension.common.init.LAItems
import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
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

        registerCarvingToolModel(itemModelMesher, LAItems.WOOD_CARVING_TOOL, "wood")
        registerCarvingToolModel(itemModelMesher, LAItems.STONE_CARVING_TOOL, "stone")
        registerCarvingToolModel(itemModelMesher, LAItems.IRON_CARVING_TOOL, "iron")
        registerCarvingToolModel(itemModelMesher, LAItems.DIAMOND_CARVING_TOOL, "diamond")
        registerCarvingToolModel(itemModelMesher, LAItems.GOLD_CARVING_TOOL, "gold")
    }

    private fun registerStepladderModel(itemModelMesher: ItemModelMesher, stepladder: ItemStepladder, type: String) {
        itemModelMesher.register(stepladder, 0, ModelResourceLocation("literalascension:${type}_stepladder", "inventory"))
    }

    private fun registerCarvingToolModel(itemModelMesher: ItemModelMesher, carvingTool: ItemCarvingTool, type: String) {
        itemModelMesher.register(carvingTool, 0, ModelResourceLocation("literalascension:${type}_carving_tool", "inventory"))
    }
}
