package element.elem;


public interface ScrollElem<I, E extends ScrollElem<I, E>> extends Elem<I, E> {

    int currentX();

    int currentY();

    E setX(int x);

    E setY(int y);

    default E set(int x, int y) {
        setX(x);
        setY(y);
        return self();
    }

    int getContentWidth();

    int getContentHeight();


}