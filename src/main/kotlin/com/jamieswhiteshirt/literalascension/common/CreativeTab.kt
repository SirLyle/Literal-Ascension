package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.LiteralAscension
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
                    return ItemStack(stepladder.item)
                }
            }
        }
        val CARVING = LiteralAscension.FEATURES.CARVING
        if (CARVING != null) {
            val CARVING_TOOLS = CARVING.CARVING_TOOLS
            if (CARVING_TOOLS != null) {
                for (carvingTool in CARVING_TOOLS.subFeatures) {
                    return ItemStack(carvingTool.item)
                }
            }
        }
        val CLIMBING_ROPE = LiteralAscension.FEATURES.CLIMBING_ROPE
        if (CLIMBING_ROPE != null) {
            return ItemStack(CLIMBING_ROPE.item)
        }
        return ItemStack(Blocks.LADDER)
    }
}
