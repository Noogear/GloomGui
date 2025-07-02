package cn.gloomGui.item.modifier;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public interface ItemModifier<T> {

    @NotNull T modify(T original, OfflinePlayer player);

    boolean loadFromObject(Object value);


}
