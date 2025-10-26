package element.elem;


public interface PageElem<E extends PageElem<E>> extends Elem<E> {

    int currentPage();

    int maxPages();

    E setPage(int page);

    default E nextPage() {
        return setPageSafely(currentPage() + 1);
    }

    default E previousPage() {
        return setPageSafely(currentPage() - 1);
    }

    /**
     * 跳转到第一页
     */
    default E firstPage() {
        return setPageSafely(1);
    }

    /**
     * 跳转到最后一页
     */
    default E lastPage() {
        return setPageSafely(maxPages());
    }

    /**
     * 跳转到下一页，如果到达末尾则循环到第一页。
     *
     * @return 实例自身
     */
    default E nextPageCircular() {
        if (maxPages() == 0) return self();
        int next = currentPage() >= maxPages() ? 1 : currentPage() + 1;
        return setPage(next);
    }

    /**
     * 跳转到上一页，如果到达开头则循环到最后一页。
     *
     * @return 实例自身
     */
    default E previousPageCircular() {
        if (maxPages() == 0) return self();
        int prev = currentPage() <= 1 ? maxPages() : currentPage() - 1;
        return setPage(prev);
    }


    /**
     * @return 是否存在下一页
     */
    default boolean hasNextPage() {
        return maxPages() > 0 && currentPage() < maxPages();
    }

    /**
     * @return 是否存在上一页
     */
    default boolean hasPreviousPage() {
        return maxPages() > 0 && currentPage() > 1;
    }


    /**
     * 一个带边界检查和安全防护的内部 setPage 调用方法。
     *
     * @param desiredPage 期望跳转的页面
     * @return 实例自身
     */
    private E setPageSafely(int desiredPage) {
        int totalPages = maxPages();
        if (totalPages <= 0) {
            return self();
        }
        int targetPage = Math.max(1, Math.min(desiredPage, totalPages));
        return setPage(targetPage);
    }
}