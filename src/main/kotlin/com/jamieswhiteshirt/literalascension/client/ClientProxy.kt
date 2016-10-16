package com.jamieswhiteshirt.literalascension.client

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.client.network.message.MessageBlockCarvedHandler
import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.carvingtool.CarvingTool
import com.jamieswhiteshirt.literalascension.common.init.CarvingTools
import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import com.jamieswhiteshirt.literalascension.common.init.Stepladders
import com.jamieswhiteshirt.literalascension.common.network.message.MessageBlockCarved
import com.jamieswhiteshirt.literalascension.common.stepladder.Stepladder
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemModelMesher
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.Side

class ClientProxy : CommonProxy() {
    override fun registerRenderers() {
        val itemModelMesher = Minecraft.getMinecraft().renderItem.itemModelMesher

        for (stepladder in Stepladders.getRegisteredStepladders()) {
            registerStepladderModel(itemModelMesher, stepladder)
        }

        for (carvingTool in CarvingTools.getRegisteredCarvingTools()) {
            registerCarvingToolModel(itemModelMesher, carvingTool)
        }

        itemModelMesher.register(Item.getItemFromBlock(LABlocks.CLIMBING_ROPE), 0, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
        itemModelMesher.register(Item.getItemFromBlock(LABlocks.CLIMBING_ROPE), 1, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
        itemModelMesher.register(Item.getItemFromBlock(LABlocks.CLIMBING_ROPE), 2, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
        itemModelMesher.register(Item.getItemFromBlock(LABlocks.CLIMBING_ROPE), 3, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
    }

    private fun registerStepladderModel(itemModelMesher: ItemModelMesher, stepladder: Stepladder) {
        itemModelMesher.register(stepladder.item, 0, ModelResourceLocation("literalascension:${stepladder.name}_stepladder", "inventory"))
    }

    private fun registerCarvingToolModel(itemModelMesher: ItemModelMesher, carvingTool: CarvingTool) {
        itemModelMesher.register(carvingTool.item, 0, ModelResourceLocation("literalascension:${carvingTool.name}_carving_tool", "inventory"))
    }

    override fun registerMessages() {
        super.registerMessages()
        LiteralAscension.packetHandler.registerMessage(MessageBlockCarvedHandler(), MessageBlockCarved::class.java, MessageBlockCarved.DISCRIMINATOR, Side.CLIENT)
    }
}
