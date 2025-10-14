package element;

import org.bukkit.inventory.ItemStack;

public interface PlaceableElement {

    boolean tryAcceptItem(ItemStack itemToAccept);

    boolean isEmpty();

    void clear();

}
