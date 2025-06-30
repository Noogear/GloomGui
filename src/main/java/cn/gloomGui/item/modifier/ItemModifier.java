package cn.gloomGui.item.modifier;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemModifier<T> {

    @NotNull T modify(T original);

    void loadFromObject(Object value);


}
