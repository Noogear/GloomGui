package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CustomModelData implements ItemModifier<ItemStack> {
    @Override
    public @NotNull ItemStack modify(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.setCustomModelData(ObjectUtil.toInt(value));
        });
        return stack;
    }
}
