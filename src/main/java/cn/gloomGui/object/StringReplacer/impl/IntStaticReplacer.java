package cn.gloomGui.object.StringReplacer.impl;

import cn.gloomGui.object.StringReplacer.IntReplacer;
import org.bukkit.OfflinePlayer;

public class IntStaticReplacer implements IntReplacer {
    private final int staticValue;

    public IntStaticReplacer(String string) {
        this.staticValue = Integer.parseInt(string);
    }

    public IntStaticReplacer(int staticValue) {
        this.staticValue = staticValue;
    }

    @Override
    public int get(OfflinePlayer player) {
        return staticValue;
    }
}
