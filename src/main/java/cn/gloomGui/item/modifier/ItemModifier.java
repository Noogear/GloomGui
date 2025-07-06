package cn.gloomGui.item.modifier;

import cn.gloomGui.item.cache.ReplacerCache;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ItemModifier<T> {

    @NotNull T modify(T original, ReplacerCache replacerCache);

    boolean initFromObject(ItemStack original, Object value);


}
