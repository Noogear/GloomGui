package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HideTooltipModifier implements ItemMetaModifier {
    private boolean metaValue;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        meta.setHideTooltip(metaValue);
        return meta;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        metaValue = ObjectUtil.toBoolean(value);
        return true;
    }
}
