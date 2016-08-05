package com.jamieswhiteshirt.literalascension.client

import com.jamieswhiteshirt.literalascension.common.CommonProxy
import com.jamieswhiteshirt.literalascension.common.init.LAItems
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.ModelResourceLocation

class ClientProxy : CommonProxy() {
    override fun registerRenderers() {
        val itemModelMesher = Minecraft.getMinecraft().renderItem.itemModelMesher

        itemModelMesher.register(LAItems.OAK_STEPLADDER, 0, ModelResourceLocation("literalascension:oak_stepladder", "inventory"))
        itemModelMesher.register(LAItems.SPRUCE_STEPLADDER, 0, ModelResourceLocation("literalascension:spruce_stepladder", "inventory"))
        itemModelMesher.register(LAItems.BIRCH_STEPLADDER, 0, ModelResourceLocation("literalascension:birch_stepladder", "inventory"))
        itemModelMesher.register(LAItems.JUNGLE_STEPLADDER, 0, ModelResourceLocation("literalascension:jungle_stepladder", "inventory"))
        itemModelMesher.register(LAItems.ACACIA_STEPLADDER, 0, ModelResourceLocation("literalascension:acacia_stepladder", "inventory"))
        itemModelMesher.register(LAItems.DARK_OAK_STEPLADDER, 0, ModelResourceLocation("literalascension:dark_oak_stepladder", "inventory"))
        itemModelMesher.register(LAItems.IRON_STEPLADDER, 0, ModelResourceLocation("literalascension:iron_stepladder", "inventory"))
    }
}
