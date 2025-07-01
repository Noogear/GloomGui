package cn.gloomGui.item.modifier;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemModifier<T> {

    @NotNull T modify(T original, OfflinePlayer player);

    boolean loadFromObject(Object value);


}
