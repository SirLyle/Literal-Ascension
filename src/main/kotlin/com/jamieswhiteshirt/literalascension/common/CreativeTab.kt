package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.LiteralAscension
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item

object CreativeTab : CreativeTabs("literalascension.tab") {
    override fun getTabIconItem(): Item = LiteralAscension.FEATURES.STEPLADDERS?.subFeatures?.firstOrNull()?.subFeatures?.firstOrNull()?.item ?:
            LiteralAscension.FEATURES.CLIMBING_ROPE?.item ?:
            LiteralAscension.FEATURES.CARVING?.CARVING_TOOLS?.subFeatures?.firstOrNull()?.item ?:
            Item.getItemFromBlock(Blocks.LADDER)!!
}
