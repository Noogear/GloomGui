package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemFlagsModifier implements ItemMetaModifier {
    private ItemFlag[] staticFlags;
    private Set<String> dynamicFlags;
    private boolean usePlaceholder;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        meta.addItemFlags(staticFlags);
        if (usePlaceholder) {
            ItemFlag[] dynamicFlags = this.dynamicFlags.stream()
                    .map(ItemFlag::valueOf)
                    .toArray(ItemFlag[]::new);
            meta.addItemFlags(dynamicFlags);
        }
        return meta;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if(value == null) {
            return false;
        }
        List<String> flags = ObjectUtil.toStringList(value);
        if (flags.isEmpty()) {
            return false;
        }
        Set<ItemFlag> staticList = new HashSet<>();
        Set<String> dynamicSet = new HashSet<>();
        for (String flag : flags) {
            if (flag == null || flag.isEmpty()) {
                continue;
            }
            if (ReplacerUtil.contains(flag)) {
                dynamicSet.add(flag);
            } else {
                staticList.add(ItemFlag.valueOf(flag));
            }
        }
        if (staticList.isEmpty() && dynamicSet.isEmpty()) {
            return false;
        }
        this.staticFlags = staticList.toArray(new ItemFlag[0]);
        this.usePlaceholder = !dynamicSet.isEmpty();
        this.dynamicFlags = usePlaceholder ? dynamicSet : Collections.emptySet();
        return true;
    }
}