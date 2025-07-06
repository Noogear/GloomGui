package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.util.AdventureUtil;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LoreModifier implements ItemMetaModifier {
    private List<ReplacerStrategy<Component>> processedLore;


    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        if (processedLore != null && !processedLore.isEmpty()) {
            meta.lore(processedLore.stream().map(loreEntry -> loreEntry.get(replacerCache)).toList());
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
        List<ReplacerStrategy<Component>> loreEntries = new ArrayList<>();
        boolean useReplacer = false;
        for (String string : stringList) {
            if (string == null || string.isEmpty()) {
                loreEntries.add(new ComponentStaticReplacer(Component.empty()));
                continue;
            }
            if (ReplacerUtil.contains(string)) {
                loreEntries.add(new ComponentDynamicReplacer(string));
                useReplacer = true;
            } else {
                loreEntries.add(new ComponentStaticReplacer(string));
            }
        }
        if (useReplacer) {
            processedLore = loreEntries;
            return true;
        } else {
            original.editMeta(meta -> {
                meta.lore(loreEntries.stream().map(loreEntry -> loreEntry.get(null)).toList());
            });
            return false;
        }
    }


    @FunctionalInterface
    private interface ReplacerStrategy<T> {
        T get(ReplacerCache replacerCache);
    }


    private record ComponentDynamicReplacer(String string) implements ReplacerStrategy<Component> {

        @Override
        public Component get(ReplacerCache replacerCache) {
            return AdventureUtil.deserialize(replacerCache.get(string));
        }
    }

    private static class ComponentStaticReplacer implements ReplacerStrategy<Component> {
        private final Component component;

        public ComponentStaticReplacer(String string) {
            this(AdventureUtil.deserialize(string));
        }

        public ComponentStaticReplacer(Component component) {
            this.component = component;
        }

        @Override
        public Component get(ReplacerCache replacerCache) {
            return component;
        }
    }
}