package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.RegistryUtils;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.StringUtil;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class EnchantmentModify implements ItemMetaModifier {
    Set<String> enchantments;

    private static EnchantmentEntry getParsed(String string) {
        String[] split;
        if (string.indexOf(',') != -1) {
            split = string.split(",", 2);
        } else {
            split = string.split(" ", 2);
        }
        Enchantment enchantment = RegistryUtils.fromString(RegistryKey.ENCHANTMENT, split[0]);
        if (enchantment == null) {
            return null;
        }
        int level = 1;
        if (split.length > 1) {
            String rawLevel = split[1].trim();
            Optional<BigDecimal> optional = StringUtil.parseNumber(rawLevel);
            if (optional.isPresent()) {
                level = optional.get().intValue();
            }
        }
        return new EnchantmentEntry(enchantment, level);
    }

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @NotNull ReplacerCache replacerCache) {
        for (String enchantment : enchantments) {
            EnchantmentEntry parsed = getParsed(enchantment);
            if (parsed != null) {
                meta.addEnchant(parsed.enchantment, parsed.level, true);
            }
        }
        return meta;
    }

    @Override
    public boolean initFromObject(ItemStack original, Object value) {
        if (value == null) {
            return false;
        }
        List<String> stringList = ObjectUtil.toStringList(value);
        if (stringList.isEmpty()) {
            return false;
        }
        Set<String> dynamicSet = new HashSet<>();
        for (String string : stringList) {
            if (string == null || string.isEmpty()) {
                continue;
            }
            if (ReplacerUtil.contains(string)) {
                dynamicSet.add(string);
            } else {
                EnchantmentEntry parsed = getParsed(string);
                if (parsed != null) {
                    original.addEnchantment(parsed.enchantment, parsed.level);
                }
            }
        }
        if (dynamicSet.isEmpty()) {
            return false;
        } else {
            this.enchantments = dynamicSet;
            return true;
        }

    }

    private static class EnchantmentEntry {
        Enchantment enchantment;
        int level;

        public EnchantmentEntry(Enchantment enchantment, int level) {
            this.enchantment = enchantment;
            this.level = level;

        }
    }


}
