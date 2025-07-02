package cn.gloomGui.item.modifier;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemMetaModifier extends ItemModifier<ItemStack> {

    @NotNull
    ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player);

    @Override
    default @NotNull ItemStack modify(@NotNull ItemStack original, @Nullable OfflinePlayer player) {
        ItemMeta itemMeta = original.getItemMeta();
        if (itemMeta != null) {
            original.setItemMeta(this.modifyMeta(itemMeta, player));
        }
        return original;
    }

}
