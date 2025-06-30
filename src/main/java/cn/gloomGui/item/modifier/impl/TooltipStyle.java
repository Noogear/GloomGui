package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.RegistryUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TooltipStyle implements ItemModifier<ItemStack> {
    private NamespacedKey tooltipStyle;

    @Override
    public @NotNull ItemStack modify(ItemStack stack) {
        if (tooltipStyle != null) {
            stack.editMeta(meta -> {
                meta.setTooltipStyle(tooltipStyle);
            });
        }
        return stack;
    }

    @Override
    public void loadFromObject(Object value) {
        tooltipStyle = RegistryUtils.toKey(ObjectUtil.toString(value));

    }
}
