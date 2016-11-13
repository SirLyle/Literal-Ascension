package com.jamieswhiteshirt.literalascension.features

import com.jamieswhiteshirt.literalascension.Features
import com.jamieswhiteshirt.literalascension.SubFeature
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import com.jamieswhiteshirt.literalascension.common.item.ItemClimbingRope
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.ShapedOreRecipe

class ClimbingRope(override val parent: Features) : SubFeature("climbing_rope", parent) {
    val block = BlockClimbingRope(this).setHardness(0.1F).setUnlocalizedName("literalascension.climbingRope") as BlockClimbingRope
    val item = ItemClimbingRope(this).setUnlocalizedName("literalascension.climbingRope").setCreativeTab(CreativeTab) as ItemClimbingRope

    fun isMatchingBlock(world: World, pos: BlockPos, facing: EnumFacing): Boolean {
        val state = world.getBlockState(pos)
        return state.block == block && state.getValue(BlockClimbingRope.FACING) == facing
    }

    override fun register() {
        GameRegistry.register(block.setRegistryName("literalascension", "climbing_rope"))
        GameRegistry.register(item.setRegistryName("literalascension", "climbing_rope"))
    }

    override fun registerRecipes() {
        CraftingManager.getInstance().addRecipe(ShapedOreRecipe(
                ItemStack(item, 8),
                "  S",
                " S ",
                "S  ",
                'S', "string"
        ))
    }

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {
        ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
    }
}
