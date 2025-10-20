package element.holder;

public interface LifeCycleHolder<H extends LifeCycleHolder<H>> {

    H onLoad();

    H onUnload();

}
