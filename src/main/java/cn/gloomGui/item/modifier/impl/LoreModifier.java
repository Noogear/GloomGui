package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.cache.ReplacerCache;
import cn.gloomGui.util.AdventureUtil;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LoreModifier implements ItemMetaModifier {
    private List<Function<ReplacerCache, Component>> processedLore;


    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        if (processedLore != null && !processedLore.isEmpty()) {
            meta.lore(processedLore.stream().map(loreEntry -> loreEntry.apply(replacerCache)).toList());
        }
        return meta;
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        List<String> stringList = ObjectUtil.toStringList(value);
        if (stringList.isEmpty()) {
            return false;
        }
        List<Function<ReplacerCache, Component>> loreEntries = new ArrayList<>();
        boolean useReplacer = false;
        for (String string : stringList) {
            if (string == null || string.isEmpty()) {
                loreEntries.add((replacerCache) -> Component.empty());
                continue;
            }
            if (ReplacerUtil.contains(string)) {
                loreEntries.add((replacerCache) -> AdventureUtil.deserialize(replacerCache.get(string)));
                useReplacer = true;
            } else {
                Component staticComponent = AdventureUtil.deserialize(string);
                loreEntries.add((replacerCache) -> staticComponent);
            }
        }
        if (useReplacer) {
            processedLore = loreEntries;
            return true;
        } else {
            original.editMeta(meta -> {
                meta.lore(loreEntries.stream().map(loreEntry -> loreEntry.apply(null)).toList());
            });
            loreEntries.clear();
            return false;
        }
    }

}