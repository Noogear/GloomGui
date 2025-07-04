package cn.gloomGui.object.stringReplacer.impl;

import cn.gloomGui.object.stringReplacer.BooleanReplacer;
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
