package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.object.StringReplacer.ReplacerHandler;
import cn.gloomGui.object.StringReplacer.impl.ComponentStaticReplacer;
import cn.gloomGui.object.StringReplacer.impl.ComponentDynamicReplacer;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LoreModifier implements ItemMetaModifier {
    private List<ReplacerHandler<Component>> processedLore;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        if (processedLore == null) {
            return meta;
        }
        meta.lore(processedLore.stream().map(loreEntry -> loreEntry.get(player)).toList());
        return meta;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        List<String> stringList = ObjectUtil.toStringList(value);
        if (stringList.isEmpty()) {
            return false;
        }
        List<ReplacerHandler<Component>> loreEntries = new ArrayList<>();
        for (String string : stringList) {
            if (string == null || string.isEmpty()) {
                loreEntries.add(new ComponentStaticReplacer(Component.empty()));
                continue;
            }
            if (ReplacerUtil.contains(string)) {
                loreEntries.add(new ComponentDynamicReplacer(string));
            } else {
                loreEntries.add(new ComponentStaticReplacer(string));
            }
        }
        processedLore = loreEntries;
        return true;
    }

}