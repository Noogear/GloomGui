package cn.gloomGui.object.StringReplacer.impl;

import cn.gloomGui.object.StringReplacer.BooleanReplacer;
import org.bukkit.OfflinePlayer;

public class BooleanStaticReplacer implements BooleanReplacer {
    private final boolean staticValue;

    public BooleanStaticReplacer(boolean staticValue) {
        this.staticValue = staticValue;
    }

    public BooleanStaticReplacer(String dynamicValue) {
        this.staticValue = Boolean.parseBoolean(dynamicValue);
    }

    @Override
    public boolean get(OfflinePlayer player) {
        return staticValue;
    }

}
