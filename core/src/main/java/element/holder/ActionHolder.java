package element.holder;

public interface ActionHolder<H extends ActionHolder<H>> {

    H onClick(int slot);

}
