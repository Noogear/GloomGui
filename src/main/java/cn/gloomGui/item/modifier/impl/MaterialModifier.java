package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MaterialModifier implements ItemModifier<ItemStack> {


    @Override
    public @NotNull ItemStack modify(ItemStack stack, Object value) {
        setMaterial(stack, ObjectUtil.toString(value));
        return stack;
    }

    private boolean setMaterial(ItemStack itemStack, String materialString) {
        String[] split = materialString.split(":", 2);
        Material material = getMaterial(split[0].trim());
        if (material != null) {
            itemStack.setType(material);
            if (split.length > 1) {
                itemStack.setDurability(Short.parseShort(split[1].trim()));
            }
            return true;
        }
        return false;
    }

    private Material getMaterial(String materialString) {
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
