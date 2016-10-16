package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.init.EnumStepladder
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object CreativeTab : CreativeTabs("literalascension.tab") {
    override fun getTabIconItem(): Item = EnumStepladder.OAK.item
}
