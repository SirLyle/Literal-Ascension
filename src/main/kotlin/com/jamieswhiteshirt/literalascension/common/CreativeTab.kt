package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.util.asStack
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

object CreativeTab : CreativeTabs("literalascension.tab") {
    override fun getTabIconItem(): ItemStack {
        val STEPLADDERS = LiteralAscension.FEATURES.STEPLADDERS
        if (STEPLADDERS != null) {
            for (domain in STEPLADDERS.subFeatures) {
                for (stepladder in domain.subFeatures) {
                    return stepladder.item.asStack()
                }
            }
        }
        val CARVING = LiteralAscension.FEATURES.CARVING
        if (CARVING != null) {
            val CARVING_TOOLS = CARVING.CARVING_TOOLS
            if (CARVING_TOOLS != null) {
                for (carvingTool in CARVING_TOOLS.subFeatures) {
                    return carvingTool.item.asStack()
                }
            }
        }
        val CLIMBING_ROPE = LiteralAscension.FEATURES.CLIMBING_ROPE
        if (CLIMBING_ROPE != null) {
            return CLIMBING_ROPE.item.asStack()
        }
        return Item.getItemFromBlock(Blocks.LADDER).asStack()
    }
}
