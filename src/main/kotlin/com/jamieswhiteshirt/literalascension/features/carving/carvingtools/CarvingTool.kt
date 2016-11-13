package com.jamieswhiteshirt.literalascension.features.carving.carvingtools

import com.jamieswhiteshirt.literalascension.SubFeature
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import com.jamieswhiteshirt.literalascension.features.carving.CarvingTools
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class CarvingTool(val ingredient: Any, toolMaterial: Item.ToolMaterial, val name: String, val unlocalizedName: String, override val parent: CarvingTools) : SubFeature(name, parent) {
    val item: ItemCarvingTool = ItemCarvingTool(this, toolMaterial).setUnlocalizedName("literalascension.carvingTool.$unlocalizedName") as ItemCarvingTool

    constructor(ingredient: Any, toolMaterial: Item.ToolMaterial, name: String, parent: CarvingTools) : this(ingredient, toolMaterial, name, name, parent)

    override fun register() {
        item.creativeTab = CreativeTab

        GameRegistry.register(item.setRegistryName("literalascension", "${name}_carving_tool"))
    }

    override fun registerRecipes() {
        CraftingManager.getInstance().addRecipe(
                ItemStack(item),
                "  M",
                " M ",
                "S  ",
                'M', ingredient, 'S', Items.STICK
        )
    }

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {
        ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation("literalascension:${name}_carving_tool", "inventory"))
    }
}
