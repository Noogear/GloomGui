package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.object.StringReplacer.IntReplacer;
import cn.gloomGui.object.StringReplacer.impl.IntDynamicReplacer;
import cn.gloomGui.object.StringReplacer.impl.IntStaticReplacer;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AmountModifier implements ItemModifier<ItemStack> {
    private IntReplacer amount;

    @Override
    public @NotNull ItemStack modify(ItemStack original, OfflinePlayer player) {
        if (amount == null) {
            return original;
        }
        original.setAmount(amount.get(player));
        return original;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Number) {
            amount = new IntStaticReplacer(ObjectUtil.toInt(value));
            return true;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.amount = new IntDynamicReplacer(string);
        } else {
            this.amount = new IntStaticReplacer(ObjectUtil.toInt(value));
        }
        return true;
    }
}
