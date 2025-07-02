package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.object.StringReplacer.BooleanReplacer;
import cn.gloomGui.object.StringReplacer.impl.BooleanDynamicReplacer;
import cn.gloomGui.object.StringReplacer.impl.BooleanStaticReplacer;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShinyModify implements ItemMetaModifier {
    private BooleanReplacer shiny;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        if (shiny != null) {
            meta.setEnchantmentGlintOverride(shiny.get(player));
        }
        return meta;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.shiny = new BooleanDynamicReplacer(string);
        } else {
            this.shiny = new BooleanStaticReplacer(ObjectUtil.toBoolean(value));
        }
        return true;
    }
}
