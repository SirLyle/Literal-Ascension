package com.jamieswhiteshirt.literalascension.common

import com.jamieswhiteshirt.literalascension.common.eventhandler.HarvestCheckEvenHandler
import net.minecraftforge.common.MinecraftForge

abstract class CommonProxy {
    abstract fun registerRenderers()

    open fun registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(HarvestCheckEvenHandler)
    }

    open fun registerMessages() { }
}
