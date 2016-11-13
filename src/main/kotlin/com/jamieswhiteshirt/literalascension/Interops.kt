package com.jamieswhiteshirt.literalascension

import com.jamieswhiteshirt.literalascension.interop.tconstruct.ITConstructInterop
import com.jamieswhiteshirt.literalascension.interop.tconstruct.impl.TConstructInterop
import net.minecraftforge.fml.common.Optional
import net.minecraftforge.fml.common.eventhandler.Event
import net.minecraftforge.fml.common.eventhandler.EventBus
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object Interops {
    class InitializationEvent : Event()

    fun initialize() {
        EventBus().let {
            it.register(this)
            it.post(InitializationEvent())
        }
    }

    const val TCONSTRUCT_MODID = "tconstruct"
    var TCONSTRUCT_INTEROP: ITConstructInterop? = null
        private set

    @Optional.Method(modid = TCONSTRUCT_MODID)
    @SubscribeEvent
    fun pluginTConstruct(@Suppress("UNUSED_PARAMETER") event: InitializationEvent) {
        TCONSTRUCT_INTEROP = TConstructInterop()
    }
}
