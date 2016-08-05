package com.jamieswhiteshirt.literalascension.common

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item

object CreativeTab : CreativeTabs("tabLiteralAscension") {
    override fun getTabIconItem(): Item = Item.getItemFromBlock(Blocks.LADDER)!!
}
