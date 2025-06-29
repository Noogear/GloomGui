package cn.gloomGui.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

public class IconDisplay implements Cloneable {
    private final ItemStack itemStack;

    private IconDisplay(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static IconDisplay of(ItemStack itemStack) {
        return new IconDisplay(itemStack);
    }

    public static IconDisplay of(Material material) {
        return new IconDisplay(ItemStack.of(material));
    }

    public static IconDisplay of(Material material, int amount) {
        return new IconDisplay(ItemStack.of(material, amount));
    }

    public <M extends ItemMeta> IconDisplay modify(Class<M> metaClass, Consumer<? super M> consumer) {
        itemStack.editMeta(metaClass, consumer);
        return this;
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
