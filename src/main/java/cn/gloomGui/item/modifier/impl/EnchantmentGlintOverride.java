package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnchantmentGlintOverride implements ItemModifier<ItemStack> {
    @Override
    public @NotNull ItemStack modify(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.setEnchantmentGlintOverride(ObjectUtil.toBoolean(value));
        });
        return stack;
    }
}
