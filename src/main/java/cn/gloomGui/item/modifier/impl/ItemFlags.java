package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ComponentHandler;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class ItemFlags implements ComponentHandler {
    @Override
    public void apply(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.addItemFlags(ObjectUtil.toStringList(value)
                    .stream()
                    .map(ItemFlag::valueOf)
                    .toArray(ItemFlag[]::new));
        });
    }
}
