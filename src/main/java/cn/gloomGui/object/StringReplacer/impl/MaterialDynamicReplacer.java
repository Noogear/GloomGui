package cn.gloomGui.object.StringReplacer.impl;

import cn.gloomGui.object.StringReplacer.MaterialReplacer;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public class MaterialDynamicReplacer implements MaterialReplacer {
    private final String dynamicValue;

    public MaterialDynamicReplacer(String dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    @Override
    public ItemStack modify(ItemStack itemStack, OfflinePlayer player) {
        return setMaterial(itemStack, ReplacerUtil.apply(dynamicValue, player));
    }

    @Override
    public ItemStack get(OfflinePlayer player) {
        return setMaterial(DEFAULT.clone(), ReplacerUtil.apply(dynamicValue, player));
    }

    private ItemStack setMaterial(ItemStack itemStack, String materialString) {
        String[] split = materialString.split(":", 2);
        Material material = getMaterial(split[0].trim());
        if (material != null) {
            itemStack.setType(material);
            if (split.length > 1) {
                itemStack.setDurability(Short.parseShort(split[1].trim()));
            }
            return itemStack;
        }
        return itemStack;
    }
}
