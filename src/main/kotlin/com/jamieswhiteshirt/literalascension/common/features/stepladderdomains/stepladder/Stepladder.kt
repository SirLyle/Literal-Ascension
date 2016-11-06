package com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.SubFeature
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.features.stepladderdomains.StepladderDomain
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import net.minecraft.block.state.IBlockState
import net.minecraftforge.fml.common.registry.GameRegistry

abstract class Stepladder(val modelState: IBlockState, val name: String, val unlocalizedName: String, override val parent: StepladderDomain) : SubFeature(name, parent) {
    val block = BlockStepladder(this).setHardness(0.5F).setUnlocalizedName("literalascension.stepladder.$unlocalizedName") as BlockStepladder
    val item = ItemStepladder(this).setUnlocalizedName("literalascension.stepladder.$unlocalizedName") as ItemStepladder

    constructor(modelState: IBlockState, name: String, parent: StepladderDomain) : this(modelState, name, name, parent)

    override fun register() {
        item.creativeTab = CreativeTab

        GameRegistry.register(block.setRegistryName("literalascension", "${parent.domainName}/${name}_stepladder"))
        GameRegistry.register(item.setRegistryName("literalascension", "${parent.domainName}/${name}_stepladder"))
    }
}
