package cn.gloomGui.builder;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.item.modifier.impl.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ItemModifierBuilder {
    private static final Map<String, Supplier<ItemModifier<ItemStack>>> HANDLER_REGISTRY = new HashMap<>();

    /**
     * 注册新的物品修饰器
     *
     * @param creator 修饰器工厂
     * @param aliases 支持的别名列表（不区分大小写）
     */
    public static void register(Supplier<ItemModifier<ItemStack>> creator, String... aliases) {
        for (String alias : aliases) {
            HANDLER_REGISTRY.put(alias.toLowerCase(Locale.ENGLISH), creator);
        }
    }

    private static void registerDefaultHandlers() {
        register(AmountModifier::new, "amount");
        register(CustomModelDataModifier::new, "custom_model_data", "model_data", "custom_model");
        register(DisplayNameModifier::new, "custom_name", "name", "display_name");
        register(HideTooltipModifier::new, "hide_tooltip");
        register(ItemComponentModifier::new, "item_component", "nbt", "component");
        register(ItemFlagsModifier::new, "item_flags", "flags", "item_flag", "flag");
        register(ItemModelModifier::new, "item_model");
        register(LoreModifier::new, "lore", "lores");
        register(MaterialModifier::new, "material", "mat", "id");
        register(ProfileModifier::new, "profile", "player_profile", "skull", "head", "player_head");
        register(ShinyModify::new, "enchantment_glint_override", "shiny");
        register(TooltipStyleModifier::new, "tooltip_style");
    }

    /**
     * 移除已注册的修饰器别名
     *
     * @param aliases 要移除的别名列表
     */
    public static void remove(String... aliases) {
        for (String alias : aliases) {
            HANDLER_REGISTRY.remove(alias.toLowerCase(Locale.ENGLISH));
        }
    }

    /**
     * 清空所有已注册的修饰器
     */
    public static void clear() {
        HANDLER_REGISTRY.clear();
    }

    /**
     * 构建物品修饰器列表
     *
     * @param map 包含配置键值对的Map
     * @return 构建完成的修饰器列表
     */
    public List<ItemModifier<ItemStack>> build(Map<String, Object> map) {
        return map.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey().toLowerCase(Locale.ENGLISH);
                    Supplier<ItemModifier<ItemStack>> supplier = HANDLER_REGISTRY.get(key);
                    if (supplier != null) {
                        ItemModifier<ItemStack> modifier = supplier.get();
                        if (modifier.loadFromObject(entry.getValue())) {
                            return modifier;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}