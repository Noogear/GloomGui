package cn.gloomGui.object.StringReplacer.impl;

import cn.gloomGui.object.StringReplacer.IntReplacer;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;

public class IntDynamicReplacer implements IntReplacer {
    private final String string;

    public IntDynamicReplacer(String string) {
        this.string = string;
    }

    @Override
    public int get(OfflinePlayer player) {
        return Integer.parseInt(ReplacerUtil.apply(string, player));
    }
}
