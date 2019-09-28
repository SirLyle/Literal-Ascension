package com.jamieswhiteshirt.literalascension.util

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

fun Item.asStack(amount: Int = 1) = ItemStack(this, amount)