package com.jamieswhiteshirt.literalascension.features.stepladderdomains.stepladder

import com.jamieswhiteshirt.literalascension.SubFeature
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import com.jamieswhiteshirt.literalascension.common.item.ItemStepladder
import com.jamieswhiteshirt.literalascension.features.stepladderdomains.StepladderDomain
import net.minecraft.block.Block.spawnAsEntity
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class Stepladder(val material: Material, val name: String, override val parent: StepladderDomain) : SubFeature(name, parent) {
    val block = BlockStepladder(this).setHardness(0.5F).setUnlocalizedName("literalascension.stepladder.${parent.domainName}.$name") as BlockStepladder
    open val item = ItemStepladder(this).setUnlocalizedName("literalascension.stepladder.${parent.domainName}.$name") as ItemStepladder

    abstract val flammability: Int
    abstract val fireSpreadSpeed: Int

    open fun onPickUp(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, drops: List<ItemStack>) {
        if (!player.isCreative) {
            drops
                    .filter { !player.inventory.addItemStackToInventory(it) && it.count > 0 }
                    .forEach { spawnAsEntity(world, pos, it) }
        }
    }

    override fun register() {
        item.creativeTab = CreativeTab

        ForgeRegistries.BLOCKS.register(block.setRegistryName("literalascension", "${parent.domainName}/${name}_stepladder"))
        ForgeRegistries.ITEMS.register(item.setRegistryName("literalascension", "${parent.domainName}/${name}_stepladder"))
    }

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {
        ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation("literalascension:${parent.domainName}/${name}_stepladder", "inventory"))
    }
}
