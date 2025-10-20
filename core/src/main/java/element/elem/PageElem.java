package element.elem;

public abstract class PageElem<E extends PageElem<E>> implements DynamicElem<E> {
    int currentPage = 0;

    public E nextPage() {
        currentPage++;
        return refresh();
    }

    public E previousPage() {
        if (currentPage > 0) {
            currentPage--;
        }
        return refresh();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public E setCurrentPage(int page) {
        this.currentPage = page;
        return refresh();
    }

}
