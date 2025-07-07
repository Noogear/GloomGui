package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.StringUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class AmountModifier implements ItemModifier<ItemStack> {
    private String amount;

    @Override
    public @NotNull ItemStack modify(ItemStack original, ReplacerCache replacerCache) {
        StringUtil.parseNumber(replacerCache.get(amount)).map(BigDecimal::intValue).ifPresent(original::setAmount);
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
            StringUtil.parseNumber(string).map(BigDecimal::intValue).ifPresent(original::setAmount);
            return false;
        }
    }
}
