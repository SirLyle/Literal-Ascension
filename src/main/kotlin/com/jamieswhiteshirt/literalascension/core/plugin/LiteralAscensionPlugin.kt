package com.jamieswhiteshirt.literalascension.core.plugin

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin

@IFMLLoadingPlugin.Name("literalascension")
@IFMLLoadingPlugin.MCVersion("1.10.2")
@IFMLLoadingPlugin.SortingIndex(1003)
class LiteralAscensionPlugin : IFMLLoadingPlugin {
    override fun getModContainerClass(): String? = null

    override fun getASMTransformerClass(): Array<out String> = arrayOf("com.jamieswhiteshirt.literalascension.core.patcher.LiteralAscensionRuntimePatcher")

    override fun getSetupClass(): String? = null

    override fun injectData(data: MutableMap<String, Any>?) { }

    override fun getAccessTransformerClass(): String? = null
}