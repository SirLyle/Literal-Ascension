package com.jamieswhiteshirt.literalascension.common.features.carving.carvingtools

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.SubFeature
import com.jamieswhiteshirt.literalascension.common.features.carving.CarvingTools
import com.jamieswhiteshirt.literalascension.common.item.ItemCarvingTool
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.fml.common.registry.GameRegistry

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
}
