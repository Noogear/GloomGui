package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MaterialModifier implements ItemModifier<ItemStack> {
    private String material;

    private static ItemStack setMaterial(ItemStack itemStack, String materialString) {
        String[] split = materialString.split(":", 2);
        Material material = getMaterial(split[0].trim());
        if (material != null) {
            ItemStack newStack = itemStack.withType(material);
            if (split.length > 1) {
                newStack.setDurability(Short.parseShort(split[1].trim()));
            }
            return newStack;
        }
        return itemStack;
    }

    private static Material getMaterial(String materialString) {
        materialString = materialString.replace(" ", "_");
        Material material;
        try {
            material = Material.matchMaterial(materialString);
        } catch (Exception ignored) {
            material = Material.AIR;
        }
        return material;
    }

    @Override
    public @NotNull ItemStack modify(ItemStack original, ReplacerCache replacerCache) {
        return setMaterial(original, replacerCache.get(material));
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.material = string;
            return true;
        } else {
            setMaterial(original, string);
            return false;
        }
    }
}
