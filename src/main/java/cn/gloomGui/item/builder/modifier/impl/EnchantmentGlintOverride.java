package cn.gloomGui.item.builder.modifier.impl;

import cn.gloomGui.item.builder.modifier.ComponentHandler;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.inventory.ItemStack;

public class EnchantmentGlintOverride implements ComponentHandler {
    @Override
    public void apply(ItemStack stack, Object value) {
        stack.editMeta(meta -> {
            meta.setEnchantmentGlintOverride(ObjectUtil.toBoolean(value));
        });
    }
}
