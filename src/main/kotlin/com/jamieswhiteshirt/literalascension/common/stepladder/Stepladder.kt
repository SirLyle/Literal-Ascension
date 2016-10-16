package com.jamieswhiteshirt.literalascension.common.stepladder

import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.block.material.Material

abstract class Stepladder(val material: Material, val name: String, val unlocalizedName: String) {
    val block = BlockStepladder(this).setHardness(0.5F).setUnlocalizedName("literalascension.stepladder.$unlocalizedName") as BlockStepladder
    val item = ItemStepladder(this).setUnlocalizedName("literalascension.stepladder.$unlocalizedName") as ItemStepladder

    constructor(material: Material, name: String) : this(material, name, name)

    abstract fun registerRecipes()
}
