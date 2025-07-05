package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.StringUtil;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemFlagsModifier implements ItemMetaModifier {
    private Set<String> dynamicFlags;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        ItemFlag[] dynamicFlags = this.dynamicFlags.stream()
                .map(flag -> ItemFlag.valueOf(StringUtil.normalizeUpper(replacerCache.get(flag))))
                .toArray(ItemFlag[]::new);
        meta.addItemFlags(dynamicFlags);
        return meta;
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        List<String> flags = ObjectUtil.toStringList(value);
        if (flags.isEmpty()) {
            return false;
        }
        Set<ItemFlag> staticSet = new HashSet<>();
        Set<String> dynamicSet = new HashSet<>();
        for (String flag : flags) {
            if (flag == null || flag.isEmpty()) {
                continue;
            }
            if (ReplacerUtil.contains(flag)) {
                dynamicSet.add(flag);
            } else {
                staticSet.add(ItemFlag.valueOf(StringUtil.normalizeUpper(flag)));
            }
        }
        if (!staticSet.isEmpty()) {
            original.editMeta(meta -> {
                meta.addItemFlags(staticSet.toArray(new ItemFlag[0]));
            });
        }
        if (dynamicSet.isEmpty()) {
            return false;
        } else {
            this.dynamicFlags = dynamicSet;
            return true;
        }
    }
}