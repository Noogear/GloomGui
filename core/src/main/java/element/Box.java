package element;

public interface Box<I> extends Container<I> {

    int getWidth();

    int getHeight();

    @Override
    default int getSize() {
        return getWidth() * getHeight();
    }

}
