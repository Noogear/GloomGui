package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ComponentHandler;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.RegistryUtils;
import org.bukkit.inventory.ItemStack;

public class TooltipStyle implements ComponentHandler {
    @Override
    public void apply(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.setTooltipStyle(RegistryUtils.toKey(ObjectUtil.toString(value)));
        });
    }
}
