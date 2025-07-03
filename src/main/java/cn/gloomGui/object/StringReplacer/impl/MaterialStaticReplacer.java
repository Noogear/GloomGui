package cn.gloomGui.object.stringReplacer.impl;

import cn.gloomGui.object.stringReplacer.MaterialReplacer;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public class MaterialStaticReplacer implements MaterialReplacer {
    private Material material;
    private short durability;

    public MaterialStaticReplacer(String string) {
        String[] split = string.split(":", 2);
        Material material = getMaterial(split[0].trim());
        if (material != null) {
            this.material = material;
            if (split.length > 1) {
                durability = Short.parseShort(split[1].trim());
            }
        }
    }

    @Override
    public ItemStack modify(ItemStack itemStack, OfflinePlayer player) {
        if (material != null) {
            itemStack.setType(material);
            if (durability != 0) {
                itemStack.setDurability(durability);
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack get(OfflinePlayer player) {
        if (material != null) {
            ItemStack stack = ItemStack.of(material);
            if (durability != 0) {
                stack.setDurability(durability);
            }
            return stack;
        }
        return DEFAULT.clone();
    }

}
