package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.SkullTextureUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ProfileModifier implements ItemModifier<ItemStack> {
    private String profile;
    private boolean usePlaceholder;

    @Override
    public @NotNull ItemStack modify(ItemStack original, OfflinePlayer player) {
        try {
            return SkullTextureUtil.itemAsync(original, usePlaceholder ? ReplacerUtil.apply(profile, player) : profile).get();
        } catch (Exception e) {
            return original;
        }
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            usePlaceholder = true;
            return true;
        } else {
            if (SkullTextureUtil.isEmpty(SkullTextureUtil.getProfileByString(string))) {
                return false;
            }
            profile = string;
            return true;
        }
    }
}
