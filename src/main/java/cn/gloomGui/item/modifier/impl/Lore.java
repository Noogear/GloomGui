package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.AdventureUtil;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Lore implements ItemModifier<ItemStack> {
    @Override
    public @NotNull ItemStack modify(ItemStack stack, Object value) {
        stack.lore(AdventureUtil.deserialize(ObjectUtil.toStringList(value)));
        return stack;
    }
}
