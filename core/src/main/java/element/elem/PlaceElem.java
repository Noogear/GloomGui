package element.elem;

import java.util.Optional;
import java.util.Set;

public interface PlaceElem<I, E extends PlaceElem<I, E>> extends Elem<I, E> {

    E placeItem(I itemToPlace, int index);

    Optional<I> removeItem(int slot);

    boolean canAccept(I itemToAccept, int slot);

    int getCapacity();

    Set<Integer> getOccupiedSlots();

}