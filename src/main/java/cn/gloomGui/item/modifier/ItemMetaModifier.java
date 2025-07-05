package cn.gloomGui.item.modifier;

import cn.gloomGui.cache.ReplacerCache;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface ItemMetaModifier extends ItemModifier<ItemStack> {

    @NotNull
    ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache);

    @Override
    default @NotNull ItemStack modify(@NotNull ItemStack original, @NotNull ReplacerCache replacerCache) {
        ItemMeta itemMeta = original.getItemMeta();
        if (itemMeta != null) {
            original.setItemMeta(this.modifyMeta(itemMeta, replacerCache));
        }
        return original;
    }

}
