package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CustomModelDataModifier implements ItemMetaModifier {
    private String customModelData;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        meta.setCustomModelData(Integer.parseInt(replacerCache.get(customModelData)));
        return meta;
    }

    @Override
    public boolean loadFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Number) {
            original.editMeta(meta -> {
                meta.setCustomModelData(ObjectUtil.toInt(value));
            });
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.customModelData = string;
            return true;
        } else {
            original.editMeta(meta -> {
                meta.setCustomModelData(ObjectUtil.toInt(value));
            });
            return false;
        }
    }
}
