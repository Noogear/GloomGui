package action;

import context.Context;
import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface ClickAction<C extends Context> {

    void run(InventoryClickEvent event, C context);

}
