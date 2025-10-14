package element;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface LifecycleElement {

    void onClose(final InventoryCloseEvent event);

    void onOpen(final InventoryOpenEvent event);

    void callBack();

}
