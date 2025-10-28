package element;

import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.Optional;

public interface Container<I> {

    int[] slots();

    int size();

    boolean contains(int slot) ;

    void render(Inventory inventory);

    Optional<I> getItemBySlot(int slot);

    Map<Integer, I> getSlotItemMap();

}
