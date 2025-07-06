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
        if (value instanceof List<?> list) {
            Set<String> stringSet = new HashSet<>();
            for (Object o : list) {
                if (o instanceof String s) {
                    if (ReplacerUtil.contains(s)) {
                        stringSet.add(s);
                    } else {
                        EnchantmentEntry parsed = getParsed(s);
                        if (parsed != null) {
                            original.addEnchantment(parsed.enchantment, parsed.level);
                        }
                    }
                }
            }
            if (stringSet.isEmpty()) {
                return false;
            }
            this.enchantments = stringSet;
            return true;
        } else {
            String string = ObjectUtil.toString(value);
            if (ReplacerUtil.contains(string)) {
                enchantments = new HashSet<>();
                enchantments.add(string);
                return true;
            } else {
                EnchantmentEntry parsed = getParsed(string);
                if (parsed != null) {
                    original.addEnchantment(parsed.enchantment, parsed.level);
                }
                return false;
            }
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
