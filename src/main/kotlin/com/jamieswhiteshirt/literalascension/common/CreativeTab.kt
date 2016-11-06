package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.LiteralAscension
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item

object CreativeTab : CreativeTabs("literalascension.tab") {
    override fun getTabIconItem(): Item? {
        val STEPLADDERS = LiteralAscension.FEATURES.STEPLADDERS
        if (STEPLADDERS != null) {
            for (domain in STEPLADDERS.subFeatures) {
                for (stepladder in domain.subFeatures) {
                    return stepladder.item
                }
            }
        }
        val CARVING = LiteralAscension.FEATURES.CARVING
        if (CARVING != null) {
            val CARVING_TOOLS = CARVING.CARVING_TOOLS
            if (CARVING_TOOLS != null) {
                for (carvingTool in CARVING_TOOLS.subFeatures) {
                    return carvingTool.item
                }
            }
        }
        return Item.getItemFromBlock(Blocks.LADDER)
    }
}
