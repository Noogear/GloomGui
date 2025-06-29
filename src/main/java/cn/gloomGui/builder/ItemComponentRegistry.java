package cn.gloomGui.builder;

import cn.gloomGui.item.builder.modifier.ComponentHandler;
import cn.gloomGui.item.builder.modifier.impl.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class ItemComponentRegistry {
    private static final Map<String, ComponentHandler> HANDLER_REGISTRY = new HashMap<>();

    static {
        registerDefaultHandlers();
    }

    private static void registerDefaultHandlers() {
        register(new Amount(), "amount");
        register(new CustomModelData(), "custom_model_data", "model_data", "custom_model");
        register(new DisplayName(), "custom_name", "name", "display_name");
        register(new EnchantmentGlintOverride(), "enchantment_glint_override", "shiny");
        register(new HideTooltip(), "hide_tooltip");
        register(new ItemModel(), "item_model");
        register(new Lore(), "lore", "lores");
        register(new TooltipStyle(), "tooltip_style");
    }

    public static void register(ComponentHandler handler, String... aliases) {
        for (String alias : aliases) {
            HANDLER_REGISTRY.put(alias.toLowerCase(Locale.ENGLISH), handler);
        }
    }

    public static void modifyItem(ItemStack stack, String property, Object value) {
        if (stack == null) throw new IllegalArgumentException("ItemStack cannot be null");
        if (property == null || property.trim().isEmpty())
            throw new IllegalArgumentException("Property cannot be null or empty");

        String key = property.toLowerCase(Locale.ENGLISH);
        ComponentHandler handler = HANDLER_REGISTRY.get(key);

        Optional.ofNullable(handler)
                .orElseThrow(() -> new IllegalArgumentException(
                        "不支持的道具属性: '" + property + "'. 可用属性: " + HANDLER_REGISTRY.keySet()
                ))
                .apply(stack, value);
    }
}