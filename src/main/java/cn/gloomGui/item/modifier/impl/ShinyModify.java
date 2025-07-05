package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ShinyModify implements ItemMetaModifier {
    private String shiny;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        meta.setEnchantmentGlintOverride(Boolean.parseBoolean(replacerCache.get(shiny)));
        return meta;
    }

    @Override
    public boolean loadFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.shiny = string;
            return true;
        } else {
            original.editMeta(meta -> {
                meta.setEnchantmentGlintOverride(Boolean.parseBoolean(string));
            });
            return false;
        }
    }
}
