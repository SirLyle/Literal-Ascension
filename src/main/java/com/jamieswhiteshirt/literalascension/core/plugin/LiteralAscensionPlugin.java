package com.jamieswhiteshirt.literalascension.core.plugin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.Name("literalascension")
@IFMLLoadingPlugin.MCVersion("1.10.2")
@IFMLLoadingPlugin.SortingIndex(1003)
public class LiteralAscensionPlugin implements IFMLLoadingPlugin {
    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"com.jamieswhiteshirt.literalascension.core.patcher.LiteralAscensionRuntimePatcher"};
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) { }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
