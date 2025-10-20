package element.elem;

import java.util.Map;

public abstract class PlaceElem<I, E extends PlaceElem<I, E>> implements DynamicElem<E> {
    Map<Integer, I> items;

    public boolean canAccept(I itemToAccept) {
        for (int slot : items.keySet()) {
            if (canAccept(itemToAccept, slot)) {
                return true;
            }
        }
        return false;
    }

    abstract boolean canAccept(I itemToAccept, int slot);

    public E placeItem(I itemToPlace, int slot) {
        if (canAccept(itemToPlace, slot)) {
            items.put(slot, itemToPlace);
            return self();
        }
        return self();
    }

    abstract boolean isEmpty();

    abstract E clear();

}
