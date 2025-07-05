package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.StringUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AmountModifier implements ItemModifier<ItemStack> {
    private String amount;

    @Override
    public @NotNull ItemStack modify(ItemStack original, ReplacerCache replacerCache) {
        StringUtil.parseInteger(replacerCache.get(amount)).ifPresent(original::setAmount);
        return original;
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
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
            StringUtil.parseInteger(string).ifPresent(original::setAmount);
            return false;
        }
    }
}
