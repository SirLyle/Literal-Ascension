package com.jamieswhiteshirt.literalascension.client

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.client.network.messagehandler.MessagePlayCarveSoundHandler
import com.jamieswhiteshirt.literalascension.client.network.messagehandler.MessagePlayLadderPickupSoundHandler
import com.jamieswhiteshirt.literalascension.client.network.messagehandler.MessageSpawnCarveParticlesHandler
import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.features.carving.carvingtools.CarvingTool
import com.jamieswhiteshirt.literalascension.common.features.stepladders.Stepladder
import com.jamieswhiteshirt.literalascension.common.network.message.MessagePlayCarveSound
import com.jamieswhiteshirt.literalascension.common.network.message.MessagePlayLadderPickupSound
import com.jamieswhiteshirt.literalascension.common.network.message.MessageSpawnCarveParticles
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemModelMesher
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.fml.relauncher.Side

class ClientProxy : CommonProxy() {
    override fun registerRenderers() {
        val itemModelMesher = Minecraft.getMinecraft().renderItem.itemModelMesher

        LiteralAscension.FEATURES.let {
            it.STEPLADDERS?.let {
                for (stepladder in it.subFeatures) {
                    registerStepladderModel(itemModelMesher, stepladder)
                }
            }
            it.CARVING?.CARVING_TOOLS?.let {
                for (carvingTool in it.subFeatures) {
                    registerCarvingToolModel(itemModelMesher, carvingTool)
                }
            }
            it.CLIMBING_ROPE?.let {
                itemModelMesher.register(it.item, 0, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
                itemModelMesher.register(it.item, 1, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
                itemModelMesher.register(it.item, 2, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
                itemModelMesher.register(it.item, 3, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
            }
        }

    }

    private fun registerStepladderModel(itemModelMesher: ItemModelMesher, stepladder: Stepladder) {
        itemModelMesher.register(stepladder.item, 0, ModelResourceLocation("literalascension:${stepladder.name}_stepladder", "inventory"))
    }

    private fun registerCarvingToolModel(itemModelMesher: ItemModelMesher, carvingTool: CarvingTool) {
        itemModelMesher.register(carvingTool.item, 0, ModelResourceLocation("literalascension:${carvingTool.name}_carving_tool", "inventory"))
    }

    override fun registerMessages() {
        super.registerMessages()
        LiteralAscension.packetHandler.registerMessage(MessagePlayCarveSoundHandler, MessagePlayCarveSound::class.java, MessagePlayCarveSound.DISCRIMINATOR, Side.CLIENT)
        LiteralAscension.packetHandler.registerMessage(MessageSpawnCarveParticlesHandler, MessageSpawnCarveParticles::class.java, MessageSpawnCarveParticles.DISCRIMINATOR, Side.CLIENT)
        LiteralAscension.packetHandler.registerMessage(MessagePlayLadderPickupSoundHandler, MessagePlayLadderPickupSound::class.java, MessagePlayLadderPickupSound.DISCRIMINATOR, Side.CLIENT)
    }
}
