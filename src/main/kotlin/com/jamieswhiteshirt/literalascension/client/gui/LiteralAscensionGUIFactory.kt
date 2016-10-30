package com.jamieswhiteshirt.literalascension.client.gui

import com.jamieswhiteshirt.literalascension.client.gui.config.LiteralAscensionConfigGUI
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.client.IModGuiFactory

class LiteralAscensionGUIFactory : IModGuiFactory {
    override fun initialize(minecraftInstance: Minecraft?) { }

    override fun mainConfigGuiClass(): Class<out GuiScreen> = LiteralAscensionConfigGUI::class.java

    override fun runtimeGuiCategories(): MutableSet<IModGuiFactory.RuntimeOptionCategoryElement>? = null

    override fun getHandlerFor(element: IModGuiFactory.RuntimeOptionCategoryElement?): IModGuiFactory.RuntimeOptionGuiHandler? = null
}