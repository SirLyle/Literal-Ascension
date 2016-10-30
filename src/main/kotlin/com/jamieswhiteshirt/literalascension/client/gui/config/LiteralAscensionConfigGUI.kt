package com.jamieswhiteshirt.literalascension.client.gui.config

import com.jamieswhiteshirt.literalascension.LiteralAscension
import net.ilexiconn.llibrary.client.gui.config.ConfigGUI
import net.ilexiconn.llibrary.client.gui.element.Element
import net.ilexiconn.llibrary.client.gui.element.color.ColorScheme
import net.minecraft.client.gui.GuiScreen

class LiteralAscensionConfigGUI(parent: GuiScreen) : ConfigGUI(parent, LiteralAscension, LiteralAscension.config) {
    var RETURN: ColorScheme = ColorScheme.create({ 0xFFFFa900.toInt() }, { 0xFFC18000.toInt() })
    var SIDEBAR: ColorScheme = ColorScheme.create({ 0xFFFFa900.toInt() }, { 0xFFC18000.toInt() })
    var ELEMENTS: ColorScheme = ColorScheme.create({ 0xFFFFa900.toInt() }, { 0xFFC18000.toInt() })

    override fun getReturnButtonColorScheme(): ColorScheme = RETURN

    override fun getSidebarColorScheme(): ColorScheme = SIDEBAR

    override fun getTopBackgroundColor(): Int = 0xFFFFa900.toInt()

    override fun getContentBackgroundColor(): Int = 0xFF5F5F5F.toInt()

    override fun getAccentColor(): Int = 0xFFFFa900.toInt()

    override fun getTextColor(): Int = 0xFFFFFFFF.toInt()

    override fun decoratePropertyElement(element: Element<ConfigGUI>) {
        element.withColorScheme(ELEMENTS)
    }
}
