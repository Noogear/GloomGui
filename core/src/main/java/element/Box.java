package element;

public interface Box<I> extends Container<I> {

    int firstSlot();

    default int lastSlot() {
        return firstSlot() + size() - 1;
    }

    @Override
    default int[] slots() {
        int size = size();
        int firstSlot = firstSlot();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = firstSlot + i;
        }
        return array;
    }

    int width();

    int height();

    @Override
    default int size() {
        return width() * height();
    }

    @Override
    default boolean contains(int slot) {
        return slot >= firstSlot() && slot <= lastSlot();
    }

}
