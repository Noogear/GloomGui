package gui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface GuiHolder extends InventoryHolder {

    default void updateTitle(final Component title) {
        final List<HumanEntity> viewers = getInventory().getViewers();
        if (!viewers.isEmpty()) {
            final String legacyTitle = LegacyComponentSerializer.legacySection().serialize(title);
            for (HumanEntity humanEntity : viewers) {
                humanEntity.getOpenInventory().setTitle(legacyTitle);
            }
        }
    }

    default void updateTitle(@NotNull final HumanEntity viewer, final Component title) {
        final InventoryView openView = viewer.getOpenInventory();
        if (openView.getTopInventory().equals(getInventory())) {
            openView.setTitle(LegacyComponentSerializer.legacySection().serialize(title));
        }
    }

    void handleClick(InventoryClickEvent event);

    void handleDrag(InventoryDragEvent event);

    void handleOpen(InventoryOpenEvent event);

    void handleClose(InventoryCloseEvent event);

    Component title();

}
