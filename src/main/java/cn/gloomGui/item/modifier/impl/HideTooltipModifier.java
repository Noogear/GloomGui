package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class HideTooltipModifier implements ItemMetaModifier {
    private String metaValue;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        meta.setHideTooltip(Boolean.parseBoolean(replacerCache.get(metaValue)));
        return meta;
    }

    @Override
    public boolean loadFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean) {
            original.editMeta(meta -> {
                meta.setHideTooltip(ObjectUtil.toBoolean(value));
            });
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.metaValue = string;
            return true;
        } else {
            original.editMeta(meta -> {
                meta.setHideTooltip(Boolean.parseBoolean(string));
            });
            return false;
        }
    }
}
