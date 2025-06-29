package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ComponentHandler;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemStack;

public class CustomModelData implements ComponentHandler {
    @Override
    public void apply(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.setCustomModelData(ObjectUtil.toInt(value));
        });
    }
}
