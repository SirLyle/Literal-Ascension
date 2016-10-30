package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.LiteralAscension
import com.jamieswhiteshirt.literalascension.common.eventhandler.HarvestCheckEventHandler
import net.minecraftforge.common.MinecraftForge

abstract class CommonProxy {
    abstract fun registerRenderers()

    open fun registerEventHandlers() {
        LiteralAscension.FEATURES.STEPLADDERS?.let {
            MinecraftForge.EVENT_BUS.register(HarvestCheckEventHandler)
        }
    }

    open fun registerMessages() {
    }
}
