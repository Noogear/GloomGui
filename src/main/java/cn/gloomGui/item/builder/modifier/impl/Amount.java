package cn.gloomGui.item.builder.modifier.impl;

import cn.gloomGui.item.builder.modifier.ComponentHandler;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemStack;

public class Amount implements ComponentHandler {
    @Override
    public void apply(ItemStack stack, Object value) {
        stack.setAmount(ObjectUtil.toInt(value));
    }
}
