package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import net.minecraft.init.Items
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.fml.common.registry.GameRegistry

object LABlocks : Module {
    val CLIMBING_ROPE = BlockClimbingRope().setUnlocalizedName("literalascension.climbingRope").setCreativeTab(CreativeTab) as BlockClimbingRope

    override fun register() {
        GameRegistry.register(CLIMBING_ROPE.setRegistryName("literalascension", "climbing_rope"))
        GameRegistry.register(ItemBlock(CLIMBING_ROPE).setRegistryName("literalascension", "climbing_rope"))
    }

    override fun registerRecipes() {
        CraftingManager.getInstance().addRecipe(
                ItemStack(CLIMBING_ROPE, 8),
                "  S",
                " S ",
                "S  ",
                'S', Items.STRING
        )
    }
}
