package com.jamieswhiteshirt.literalascension.common.features.stepladders

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.SubFeature
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.features.Stepladders
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.block.material.Material
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class Stepladder(val material: Material, val name: String, val unlocalizedName: String, override val parent: Stepladders) : SubFeature(name, parent) {
    val block = BlockStepladder(this).setHardness(0.5F).setUnlocalizedName("literalascension.stepladder.$unlocalizedName") as BlockStepladder
    val item = ItemStepladder(this).setUnlocalizedName("literalascension.stepladder.$unlocalizedName") as ItemStepladder

    constructor(material: Material, name: String, parent: Stepladders) : this(material, name, name, parent)

    override fun register() {
        item.creativeTab = CreativeTab

        GameRegistry.register(block.setRegistryName("literalascension", "${name}_stepladder"))
        GameRegistry.register(item.setRegistryName("literalascension", "${name}_stepladder"))
    }
}