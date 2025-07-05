package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.util.AdventureUtil;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class DisplayNameModifier implements ItemMetaModifier {
    private String name;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        meta.displayName(AdventureUtil.deserialize(replacerCache.get(name)));
        return meta;
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.name = string;
            return true;
        } else {
            original.editMeta(meta -> {
                meta.displayName(AdventureUtil.deserialize(string));
            });
            return false;
        }
    }
}
