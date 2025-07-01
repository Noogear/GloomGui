package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.object.StringReplacer.ReplacerHandler;
import cn.gloomGui.object.StringReplacer.impl.KeyDynamicReplacer;
import cn.gloomGui.object.StringReplacer.impl.KeyStaticReplacer;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.RegistryUtils;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TooltipStyleModifier implements ItemMetaModifier {
    private ReplacerHandler<NamespacedKey> tooltipStyle;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        if (tooltipStyle != null) {
            meta.setTooltipStyle(tooltipStyle.get(player));
        }
        return meta;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.tooltipStyle = new KeyDynamicReplacer(string);
        } else {
            NamespacedKey key = RegistryUtils.toKey(string);
            if (key == null) {
                return false;
            }
            this.tooltipStyle = new KeyStaticReplacer(key);
        }
        return true;
    }
}
