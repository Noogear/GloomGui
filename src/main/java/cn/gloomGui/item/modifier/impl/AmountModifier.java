package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AmountModifier implements ItemModifier<ItemStack> {
    private String amount;

    @Override
    public @NotNull ItemStack modify(ItemStack original, ReplacerCache replacerCache) {
        original.setAmount(Integer.parseInt(replacerCache.get(amount)));
        return original;
    }

    @Override
    public boolean loadFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Number) {
            original.setAmount(ObjectUtil.toInt(value));
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.amount = string;
            return true;
        } else {
            original.setAmount(ObjectUtil.toInt(value));
            return false;
        }
    }
}
