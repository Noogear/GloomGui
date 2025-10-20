package element.elem;

public interface DynamicElem<E extends DynamicElem<E>> {

    E refresh();

    E self();

}
