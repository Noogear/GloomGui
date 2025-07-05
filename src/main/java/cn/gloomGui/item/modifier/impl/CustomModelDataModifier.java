package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.StringUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CustomModelDataModifier implements ItemMetaModifier {
    private String customModelData;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        StringUtil.parseInteger(replacerCache.get(customModelData)).ifPresent(meta::setCustomModelData);
        return meta;
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
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
            StringUtil.parseInteger(string).ifPresent(integer -> {
                original.editMeta(meta -> {
                    meta.setCustomModelData(integer);
                });
            });
            return false;
        }
    }
}
