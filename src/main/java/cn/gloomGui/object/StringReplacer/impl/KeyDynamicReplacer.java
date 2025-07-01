package cn.gloomGui.object.StringReplacer.impl;

import cn.gloomGui.object.StringReplacer.ReplacerHandler;
import cn.gloomGui.util.RegistryUtils;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;

public class KeyDynamicReplacer implements ReplacerHandler<NamespacedKey> {
    private final String dynamicValue;

    public KeyDynamicReplacer(String dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    @Override
    public NamespacedKey get(OfflinePlayer player) {
        return RegistryUtils.toKey(ReplacerUtil.apply(dynamicValue, player));
    }
}
