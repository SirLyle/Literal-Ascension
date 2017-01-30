package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.SubFeature
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class Stepladder(val material: Material, val name: String, override val parent: StepladderDomain) : SubFeature(name, parent) {
    val block = BlockStepladder(this).setHardness(0.5F).setUnlocalizedName("literalascension.stepladder.${parent.domainName}.$name") as BlockStepladder
    val item = ItemStepladder(this).setUnlocalizedName("literalascension.stepladder.${parent.domainName}.$name") as ItemStepladder

    abstract val flammability: Int
    abstract val fireSpreadSpeed: Int

    override fun register() {
        item.creativeTab = CreativeTab

        GameRegistry.register(block.setRegistryName("literalascension", "${parent.domainName}/${name}_stepladder"))
        GameRegistry.register(item.setRegistryName("literalascension", "${parent.domainName}/${name}_stepladder"))
    }

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {
        ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation("literalascension:${parent.domainName}/${name}_stepladder", "inventory"))
    }
}
