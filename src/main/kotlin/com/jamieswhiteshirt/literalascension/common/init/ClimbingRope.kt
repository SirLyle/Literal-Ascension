package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.block.BlockClimbingRope
import com.jamieswhiteshirt.literalascension.common.item.ItemClimbingRope
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.fml.common.registry.GameRegistry

object ClimbingRope : Module {
    val block = BlockClimbingRope(this).setHardness(0.1F).setUnlocalizedName("literalascension.climbingRope") as BlockClimbingRope
    val item = ItemClimbingRope(this).setUnlocalizedName("literalascension.climbingRope").setCreativeTab(CreativeTab) as ItemClimbingRope

    override fun register() {
        GameRegistry.register(block.setRegistryName("literalascension", "climbing_rope"))
        GameRegistry.register(item.setRegistryName("literalascension", "climbing_rope"))
    }

    override fun registerRecipes() {
        CraftingManager.getInstance().addRecipe(
                ItemStack(item, 8),
                "  S",
                " S ",
                "S  ",
                'S', Items.STRING
        )
    }
}
