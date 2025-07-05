package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.StringUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ShinyModify implements ItemMetaModifier {
    private String shiny;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        StringUtil.parseBoolean(replacerCache.get(shiny)).ifPresent(meta::setEnchantmentGlintOverride);
        return meta;
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.shiny = string;
            return true;
        } else {
            StringUtil.parseBoolean(string).ifPresent(bool -> original.editMeta(meta -> {
                meta.setEnchantmentGlintOverride(bool);
            }));
            return false;
        }
    }
}
