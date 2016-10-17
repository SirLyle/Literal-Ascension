package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.carvingtool.CarvingTool
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.*

object CarvingTools : Module {
    val WOOD = CarvingTool(Blocks.PLANKS, Item.ToolMaterial.WOOD, "wood")
    val STONE = CarvingTool(Blocks.COBBLESTONE, Item.ToolMaterial.STONE, "stone")
    val IRON = CarvingTool(Items.IRON_INGOT, Item.ToolMaterial.IRON, "iron")
    val DIAMOND = CarvingTool(Items.DIAMOND, Item.ToolMaterial.DIAMOND, "diamond")
    val GOLD = CarvingTool(Items.GOLD_INGOT, Item.ToolMaterial.GOLD, "gold")

    private val registeredCarvingTools = ArrayList<CarvingTool>()

    fun getRegisteredCarvingTools(): List<CarvingTool> = registeredCarvingTools

    override fun register() {
        if (LiteralAscension.config.carvingToolWoodEnabled) registerCarvingTool(WOOD)
        if (LiteralAscension.config.carvingToolStoneEnabled) registerCarvingTool(STONE)
        if (LiteralAscension.config.carvingToolIronEnabled) registerCarvingTool(IRON)
        if (LiteralAscension.config.carvingToolDiamondEnabled) registerCarvingTool(DIAMOND)
        if (LiteralAscension.config.carvingToolGoldEnabled) registerCarvingTool(GOLD)
    }

    private fun registerCarvingTool(carvingTool: CarvingTool) {
        carvingTool.item.creativeTab = CreativeTab

        GameRegistry.register(carvingTool.item.setRegistryName("literalascension", "${carvingTool.name}_carving_tool"))

        registeredCarvingTools.add(carvingTool)
    }

    override fun registerRecipes() {
        for (carvingTool in CarvingTools.getRegisteredCarvingTools()) {
            CraftingManager.getInstance().addRecipe(
                    ItemStack(carvingTool.item),
                    "  M",
                    " M ",
                    "S  ",
                    'M', carvingTool.ingredient, 'S', Items.STICK
            )
        }
    }
}
