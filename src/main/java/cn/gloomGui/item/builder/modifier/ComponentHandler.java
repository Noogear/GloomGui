package cn.gloomGui.item.builder.modifier;

import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface ComponentHandler {

    /**
     * 应用修改到ItemStack
     *
     * @param stack 要修改的道具
     * @param value 属性值
     * @throws IllegalArgumentException 如果值类型不匹配
     */
    void apply(ItemStack stack, Object value);

}
