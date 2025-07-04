package cn.gloomGui.object.stringReplacer.impl;

import cn.gloomGui.object.stringReplacer.ReplacerStrategy;
import cn.gloomGui.util.RegistryUtils;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;

public class KeyDynamicReplacer implements ReplacerStrategy<NamespacedKey> {
    private final String dynamicValue;

    public KeyDynamicReplacer(String dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    @Override
    public NamespacedKey get(OfflinePlayer player) {
        return RegistryUtils.toKey(ReplacerUtil.apply(dynamicValue, player));
    }
}
