package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import com.jamieswhiteshirt.literalascension.common.block.BlockNotched
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import net.minecraft.item.ItemBlock
import net.minecraftforge.fml.common.registry.GameRegistry

object LABlocks {
    val CLIMBING_ROPE = BlockClimbingRope().setUnlocalizedName("literalascension.climbingRope").setCreativeTab(CreativeTab) as BlockClimbingRope

    fun register() {
        for (stepladder in EnumStepladder.values()) {
            registerStepladder(stepladder.block)
        }

        for (carvedBlocks in EnumCarvedBlocks.values()) {
            registerChute(carvedBlocks.chute)
            registerNotched(carvedBlocks.notched)
        }

        GameRegistry.register(CLIMBING_ROPE.setRegistryName("literalascension", "climbing_rope"))
        GameRegistry.register(ItemBlock(CLIMBING_ROPE).setRegistryName("literalascension", "climbing_rope"))
    }

    private fun registerStepladder(stepladder: BlockStepladder) {
        GameRegistry.register(stepladder.setRegistryName("literalascension", "${stepladder.type}_stepladder"))
    }

    private fun registerChute(chute: BlockChute) {
        GameRegistry.register(chute.setRegistryName("literalascension", "${chute.type}_chute"))
    }

    private fun registerNotched(notched: BlockNotched) {
        GameRegistry.register(notched.setRegistryName("literalascension", "${notched.type}_notched"))
    }
}
