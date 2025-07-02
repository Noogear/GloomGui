package cn.gloomGui.builder;

import cn.gloomGui.item.modifier.ItemModifier;
import cn.gloomGui.item.modifier.impl.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ItemModifierBuilder {
    private static final Map<String, ItemModifier<?>> HANDLER_REGISTRY = new HashMap<>();

    public static void register(ItemModifier<?> handler, String... aliases) {
        for (String alias : aliases) {
            HANDLER_REGISTRY.put(alias.toLowerCase(Locale.ENGLISH), handler);
        }
    }

    private void registerDefaultHandlers() {
        register(new AmountModifier(), "amount");
        register(new CustomModelDataModifier(), "custom_model_data", "model_data", "custom_model");
        register(new DisplayNameModifier(), "custom_name", "name", "display_name");
        register(new ShinyModify(), "enchantment_glint_override", "shiny");
        register(new HideTooltipModifier(), "hide_tooltip");
        register(new ItemFlagsModifier(), "item_flags", "flags", "item_flag", "flag");
        register(new ItemModelModifier(), "item_model");
        register(new LoreModifier(), "lore", "lores");
        register(new TooltipStyleModifier(), "tooltip_style");
    }

}