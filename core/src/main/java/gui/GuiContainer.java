package gui;

import context.Context;
import element.Container;
import element.IndexContainer;
import element.elem.Elem;

import java.util.List;
import java.util.Optional;

public abstract class GuiContainer<I, C extends Context, E extends Elem<I, E> & IndexContainer<I>> implements Container<I> {
    protected final E[] slotElementArray;
    protected final List<E> elementList;
    protected final C context;

    protected GuiContainer(int size, E[] slotElementArray, List<E> elementList, C context) {
        this.slotElementArray = slotElementArray;
        this.elementList = elementList;
        this.context = context;
    }

    public C guiContext() {
        return context;
    }

    public void addElement(E elem) {
        for (int slot : elem.slots()) {
            if (slot >= 0 && slot < slotElementArray.length) {
                if (slotElementArray[slot] != null) {
                    throw new RuntimeException("Slot " + slot + " is already occupied");
                }
                this.slotElementArray[slot] = elem;
            }
        }
        this.elementList.add(elem);
    }

    @Override
    public int[] slots() {
        return ;
    }

    @Override
    public int size() {
        return slotElementArray.length;
    }

    @Override
    public boolean contains(int slot) {
        return slotElementArray[slot] != null;
    }

    @Override
    public Optional<I> getItemBySlot(int slot) {
        if (slot < 0 || slot >= slotElementArray.length) {
            return Optional.empty();
        }
        return slotElementArray[slot].getItemBySlot(slot);
    }

}
