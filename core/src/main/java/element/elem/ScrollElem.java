package element.elem;

public abstract class ScrollElem<E extends ScrollElem<E>> implements DynamicElem<E> {

    int x = 0;
    int y = 0;

    public E scroll(int up, int right) {
        x = x + up;
        y = y + right;
        return refresh();
    }


}
