package com.jamieswhiteshirt.literalascension.features.carving.carvingtools

import com.jamieswhiteshirt.literalascension.SubFeature
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import com.jamieswhiteshirt.literalascension.features.carving.CarvingTools
import com.jamieswhiteshirt.literalascension.util.asStack
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.registry.ForgeRegistries
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class CarvingTool(val ingredient: Any, toolMaterial: Item.ToolMaterial, val name: String, val unlocalizedName: String, override val parent: CarvingTools) : SubFeature(name, parent) {
    val item: ItemCarvingTool = ItemCarvingTool(this, toolMaterial).setUnlocalizedName("literalascension.carvingTool.$unlocalizedName") as ItemCarvingTool

    constructor(ingredient: Any, toolMaterial: Item.ToolMaterial, name: String, parent: CarvingTools) : this(ingredient, toolMaterial, name, name, parent)

    override fun register() {
        item.creativeTab = CreativeTab

        ForgeRegistries.ITEMS.register(item.setRegistryName("literalascension", "${name}_carving_tool"))
    }

    override fun registerRecipes() {
        GameRegistry.addShapedRecipe(
                ResourceLocation("literalascension", "${name}_carvingtool"),
                ResourceLocation("literalascension", "carvingtool"),
                item.asStack(),
                "  M",
                " M ",
                "S  ",
                'M', ingredient, 'S', "stickWood"

        )
    }

    @SideOnly(Side.CLIENT)
    override fun registerRenderers() {
        ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation("literalascension:${name}_carving_tool", "inventory"))
    }
}
