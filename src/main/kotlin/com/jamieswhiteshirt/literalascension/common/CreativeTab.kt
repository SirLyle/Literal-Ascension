package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.init.LAItems
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object CreativeTab : CreativeTabs("literalascension.tab") {
    override fun getTabIconItem(): Item = LAItems.OAK_STEPLADDER
}
