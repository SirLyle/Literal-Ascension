package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.init.LABlocks
import com.jamieswhiteshirt.literalascension.common.init.Stepladders
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.Item

object CreativeTab : CreativeTabs("literalascension.tab") {
    override fun getTabIconItem(): Item = Stepladders.getRegisteredStepladders().firstOrNull()?.item ?: Item.getItemFromBlock(LABlocks.CLIMBING_ROPE) ?: Item.getItemFromBlock(Blocks.LADDER)!!
}
