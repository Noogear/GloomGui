package cn.gloomGui.object.StringReplacer.impl;

import cn.gloomGui.object.StringReplacer.ReplacerHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;

public class KeyStaticReplacer implements ReplacerHandler<NamespacedKey> {
    private final NamespacedKey staticValue;

    public KeyStaticReplacer(NamespacedKey staticValue) {
        this.staticValue = staticValue;
    }

    public KeyStaticReplacer(String dynamicValue) {
        this.staticValue = NamespacedKey.fromString(dynamicValue);
    }

    @Override
    public NamespacedKey get(OfflinePlayer player) {
        return staticValue;
    }
}
