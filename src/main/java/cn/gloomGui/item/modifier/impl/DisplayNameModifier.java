package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.object.StringReplacer.ReplacerHandler;
import cn.gloomGui.object.StringReplacer.impl.ComponentDynamicReplacer;
import cn.gloomGui.object.StringReplacer.impl.ComponentStaticReplacer;
import cn.gloomGui.util.AdventureUtil;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DisplayNameModifier implements ItemMetaModifier {
    private ReplacerHandler<Component> name;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        meta.displayName(name.get(player));
        return meta;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            name = new ComponentDynamicReplacer(string);
        } else {
            name = new ComponentStaticReplacer(string);
        }
        return true;
    }
}
