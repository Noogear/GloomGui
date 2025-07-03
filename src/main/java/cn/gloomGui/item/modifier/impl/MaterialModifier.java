package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.object.stringReplacer.MaterialReplacer;
import cn.gloomGui.object.stringReplacer.impl.MaterialDynamicReplacer;
import cn.gloomGui.object.stringReplacer.impl.MaterialStaticReplacer;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MaterialModifier implements ItemModifier<ItemStack> {
    private MaterialReplacer materialReplacer;

    @Override
    public @NotNull ItemStack modify(ItemStack original, OfflinePlayer player) {
        if (materialReplacer != null) {
            return materialReplacer.modify(original, player);
        }
        return original;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.materialReplacer = new MaterialDynamicReplacer(string);
        } else {
            this.materialReplacer = new MaterialStaticReplacer(string);
        }
        return true;
    }
}
