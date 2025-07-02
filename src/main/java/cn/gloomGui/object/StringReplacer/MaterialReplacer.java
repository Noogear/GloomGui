package cn.gloomGui.object.StringReplacer;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public interface MaterialReplacer extends ReplacerHandler<ItemStack> {
    ItemStack DEFAULT = new ItemStack(Material.AIR);

    ItemStack modify(ItemStack itemStack, OfflinePlayer player);

    default Material getMaterial(String materialString) {
        materialString = materialString.replace(" ", "_");
        Material material;
        try {
            material = Material.matchMaterial(materialString);
        } catch (Exception ignored) {
            material = Material.AIR;
        }
        return material;
    }

}
