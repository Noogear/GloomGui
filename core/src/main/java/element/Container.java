package element;

public interface Container<I> {

    int[] getSlots();

    int getSize();

    I render(int slot);

}
