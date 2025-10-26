package element.elem;

import java.util.Optional;
import java.util.Set;

public interface PlaceElem<I, E extends PlaceElem<I, E>> extends Elem<E> {

    E placeItem(I itemToPlace, int slot);

    Optional<I> removeItem(int slot);

    boolean canAccept(I itemToAccept, int slot);

    int getCapacity();

    Set<Integer> getOccupiedSlots();

    default boolean addItem(I item) {
        for (int i = 0; i < getCapacity(); i++) {
            if (!isSlotOccupied(i) && canAccept(item, i)) {
                placeItem(item, i);
                return true;
            }
        }
        return false;
    }

    default boolean isSlotOccupied(int slot) {
        return getOccupiedSlots().contains(slot);
    }

    default boolean isEmpty() {
        return getOccupiedSlots().isEmpty();
    }

    default boolean isFull() {
        if (getCapacity() == 0) return false;
        return getOccupiedSlots().size() >= getCapacity();
    }

}