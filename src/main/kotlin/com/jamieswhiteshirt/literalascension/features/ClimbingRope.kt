package com.jamieswhiteshirt.literalascension.features

import com.jamieswhiteshirt.literalascension.Features
import com.jamieswhiteshirt.literalascension.SubFeature
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import com.jamieswhiteshirt.literalascension.common.item.ItemClimbingRope
import com.jamieswhiteshirt.literalascension.util.asStack
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class ClimbingRope(override val parent: Features) : SubFeature("climbing_rope", parent) {
    val block = BlockClimbingRope(this).setHardness(0.1F).setUnlocalizedName("literalascension.climbingRope") as BlockClimbingRope
    val item = ItemClimbingRope(this).setUnlocalizedName("literalascension.climbingRope").setCreativeTab(CreativeTab) as ItemClimbingRope

    fun isMatchingBlock(world: World, pos: BlockPos, facing: EnumFacing): Boolean {
        val state = world.getBlockState(pos)
        return state.block == block && state.getValue(BlockClimbingRope.FACING) == facing
    }

    override fun register() {
        ForgeRegistries.BLOCKS.register(block.setRegistryName("literalascension", "climbing_rope"))
        ForgeRegistries.ITEMS.register(item.setRegistryName("literalascension", "climbing_rope"))
    }

    override fun registerRecipes() {
        GameRegistry.addShapedRecipe(
                ResourceLocation("literalascension", "climbing_rope"),
                ResourceLocation("literalascension", "climbing_rope"),
                item.asStack(8),
                "  S"," S ","S  ", 'S', "string"

        )
    }

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {
        ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation("literalascension:climbing_rope", "inventory"))
    }
}
