package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ItemComponentModifier implements ItemModifier<ItemStack> {
    private static final Gson GSON = new Gson();
    private String nbtData;

    @Override
    public @NotNull ItemStack modify(ItemStack original, ReplacerCache replacerCache) {
        String materialName = original.getType().getKey().toString();
        try {
            return Bukkit.getItemFactory().createItemStack(materialName + replacerCache.get(nbtData));
        } catch (Throwable throwable) {
            return original;
        }
    }

    @Override
    public boolean loadFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        String nbt;
        if (value instanceof Map<?, ?> map) {
            nbt = GSON.toJson(map);
        } else {
            nbt = ObjectUtil.toString(value);
        }
        if (ReplacerUtil.contains(nbtData)) {
            nbtData = nbt;
            return true;
        } else {
            String materialName = original.getType().getKey().toString();
            try {
                ItemStack newStack = Bukkit.getItemFactory().createItemStack(materialName + nbt);
                original.setItemMeta(newStack.getItemMeta());
            } catch (Exception ignored) {
            }
            return false;
        }
    }
}
