package element.elem;


import java.util.Collection;

public interface Elem<I, E extends Elem<I, E>> {

    E self();

    E updateSlot(int slot);

    E updateSlots(Collection<Integer> slots);

    int indexOfSlot(int slot);

    int slotOfIndex(int index);

}
