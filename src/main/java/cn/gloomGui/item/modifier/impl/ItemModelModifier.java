package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.cache.ReplacerCache;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.RegistryUtils;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ItemModelModifier implements ItemMetaModifier {
    private String itemModel;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        meta.setItemModel(RegistryUtils.toKey(replacerCache.get(itemModel)));
        return meta;
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.itemModel = string;
            return true;
        } else {
            NamespacedKey key = RegistryUtils.toKey(string);
            if (key == null) {
                return false;
            }
            original.editMeta(meta -> {
                meta.setItemModel(key);
            });
            return false;
        }
    }
}
