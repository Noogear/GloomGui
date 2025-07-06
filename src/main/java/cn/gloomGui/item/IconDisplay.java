package cn.gloomGui.item;

import cn.gloomGui.item.cache.ReplacerCache;
import cn.gloomGui.item.modifier.ItemModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public class IconDisplay implements Cloneable {
    private final ItemStack itemStack;
    private final List<ItemModifier<ItemStack>> itemModifiers;

    protected IconDisplay(ItemStack itemStack, List<ItemModifier<ItemStack>> itemModifiers) {
        this.itemStack = itemStack;
        this.itemModifiers = itemModifiers;
    }

    public static IconDisplay of(ItemStack itemStack, List<ItemModifier<ItemStack>> itemModifiers) {
        return new IconDisplay(itemStack, itemModifiers);
    }

    public static IconDisplay of(ItemStack itemStack) {
        return new IconDisplay(itemStack, List.of());
    }

    public <M extends ItemMeta> IconDisplay modify(Class<M> metaClass, Consumer<? super M> consumer) {
        itemStack.editMeta(metaClass, consumer);
        return this;
    }

    public ItemStack item(ReplacerCache replacerCache) {
        ItemStack item = itemStack.clone();
        if (itemModifiers != null) {
            for (ItemModifier<ItemStack> itemModifier : itemModifiers) {
                itemModifier.modify(item, replacerCache);
            }
        }
        return item;
    }

    @Override
    public IconDisplay clone() {
        try {
            return (IconDisplay) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
