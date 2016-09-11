package com.jamieswhiteshirt.literalascension.client.gui.config

import com.jamieswhiteshirt.literalascension.LiteralAscension
import net.ilexiconn.llibrary.client.gui.config.ConfigGUI
import net.ilexiconn.llibrary.server.config.ConfigHandler
import net.minecraft.client.gui.GuiScreen

class LiteralAscensionConfigGUI(parent: GuiScreen) : ConfigGUI(parent, LiteralAscension.INSTANCE, ConfigHandler.INSTANCE.getConfigForID("literalascension"))
