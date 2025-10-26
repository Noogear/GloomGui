package element;

public interface Box<I> extends Container<I> {

    int firstSlot();

    default int lastSlot() {
        return firstSlot() + size() - 1;
    }

    @Override
    default int[] slots() {
        final int size = size();
        final int firstSlot = firstSlot();
        final int[] array = new int[size];
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
