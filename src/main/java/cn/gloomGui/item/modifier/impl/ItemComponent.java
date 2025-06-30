package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemComponent implements ItemModifier<ItemStack> {
    @Override
    public @NotNull ItemStack modify(ItemStack stack, Object value) {
        Material material = stack.getType();
        NamespacedKey materialKey = material.getKey();
        String materialName = materialKey.toString();
        try {
            return Bukkit.getItemFactory().createItemStack(materialName + ObjectUtil.toString(value));
        } catch (Throwable throwable) {
            return stack;
        }
    }
}
