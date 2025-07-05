package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.util.ObjectUtil;
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
            return Bukkit.getItemFactory().createItemStack(materialName + nbtData);
        } catch (Throwable throwable) {
            return original;
        }
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Map<?, ?> map) {
            nbtData = GSON.toJson(map);
        } else {
            nbtData = ObjectUtil.toString(value);
        }
        return true;
    }
}
