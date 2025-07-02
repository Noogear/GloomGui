package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.object.StringReplacer.IntReplacer;
import cn.gloomGui.object.StringReplacer.impl.IntDynamicReplacer;
import cn.gloomGui.object.StringReplacer.impl.IntStaticReplacer;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomModelDataModifier implements ItemMetaModifier {
    private IntReplacer customModelData;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        if (customModelData != null) {
            meta.setCustomModelData(customModelData.get(player));
        }
        return meta;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Number) {
            customModelData = new IntStaticReplacer(ObjectUtil.toInt(value));
            return true;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.customModelData = new IntDynamicReplacer(string);
        } else {
            this.customModelData = new IntStaticReplacer(ObjectUtil.toInt(value));
        }
        return true;
    }
}
