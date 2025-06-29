package cn.gloomGui.item.builder.modifier.impl;

import cn.gloomGui.item.builder.modifier.ComponentHandler;
import cn.gloomGui.util.AdventureUtil;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemStack;

public class Lore implements ComponentHandler {
    @Override
    public void apply(ItemStack stack, Object value) {
        stack.lore(AdventureUtil.deserialize(ObjectUtil.toStringList(value)));
    }
}
