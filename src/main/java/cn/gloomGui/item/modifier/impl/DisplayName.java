package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ComponentHandler;
import cn.gloomGui.util.AdventureUtil;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemStack;

public class DisplayName implements ComponentHandler {
    @Override
    public void apply(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.displayName(AdventureUtil.deserialize(ObjectUtil.toString(value)));
        });
    }
}
