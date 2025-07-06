package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.SkullTextureUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ProfileModifier implements ItemModifier<ItemStack> {
    private String profile;

    @Override
    public @NotNull ItemStack modify(ItemStack original, ReplacerCache replacerCache) {
        try {
            return SkullTextureUtil.itemAsync(original, replacerCache.get(profile)).get();
        } catch (Exception e) {
            return original;
        }
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.profile = string;
            return true;
        } else {
            if (SkullTextureUtil.isEmpty(SkullTextureUtil.getProfileByString(string))) {
                return false;
            }
            try {
                SkullTextureUtil.itemAsync(original, string).get();
            } catch (Exception ignored) {
            }
        }
        return false;
    }
}
