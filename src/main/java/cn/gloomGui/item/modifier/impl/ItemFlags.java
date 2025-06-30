package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemFlags implements ItemModifier<ItemStack> {
    @Override
    public @NotNull ItemStack modify(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.addItemFlags(ObjectUtil.toStringList(value)
                    .stream()
                    .map(ItemFlag::valueOf)
                    .toArray(ItemFlag[]::new));
        });
        return stack;
    }
}
