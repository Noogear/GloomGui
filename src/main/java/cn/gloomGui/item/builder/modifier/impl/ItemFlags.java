package cn.gloomGui.item.builder.modifier.impl;

import cn.gloomGui.item.builder.modifier.ComponentHandler;
import cn.gloomGui.util.RegistryUtils;
import org.bukkit.Registry;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class ItemFlags implements ComponentHandler {
    @Override
    public void apply(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.addItemFlags(ItemFlag.valueOf());
        });
    }
}
