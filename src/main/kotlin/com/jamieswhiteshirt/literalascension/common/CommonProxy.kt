package com.jamieswhiteshirt.literalascension.common

abstract class CommonProxy {
    abstract fun registerRenderers()

    open fun registerEventHandlers() { }

    open fun registerMessages() { }
}
