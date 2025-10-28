package element.elem;


public interface Elem<I, E extends Elem<I, E>> {

    E self();

}
