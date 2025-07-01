package cn.gloomGui.object.StringReplacer.impl;

import cn.gloomGui.object.StringReplacer.BooleanReplacer;
import cn.gloomGui.object.StringReplacer.ReplacerHandler;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;

public class BooleanDynamicReplacer implements BooleanReplacer {
    private final String dynamicValue;

    public BooleanDynamicReplacer(String dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    @Override
    public boolean get(OfflinePlayer player) {
        return Boolean.parseBoolean(ReplacerUtil.apply(dynamicValue, player));
    }
}
