package com.jamieswhiteshirt.literalascension.common.features

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.Features
import com.jamieswhiteshirt.literalascension.common.SubFeature
import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import com.jamieswhiteshirt.literalascension.common.item.ItemClimbingRope
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
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
}
