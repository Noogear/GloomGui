package element.elem;


import java.util.Collection;

public interface Elem<E extends Elem<E>> {

    E self();

    E updateItem(int slot);

    E updateItems(Collection<Integer> slots);

}
