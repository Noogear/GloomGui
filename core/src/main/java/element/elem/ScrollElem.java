package element.elem;


public interface ScrollElem<E extends ScrollElem<E>> extends DynamicElem<E> {

    /**
     * @return 当前的水平滚动位置 (X坐标)
     */
    int currentX();

    /**
     * @return 当前的垂直滚动位置 (Y坐标)
     */
    int currentY();

    /**
     * 设置内部的水平滚动位置，但不立即刷新。
     *
     * @param x 目标X坐标
     * @return 实例自身
     */
    E setX(int x);

    /**
     * 设置内部的垂直滚动位置，但不立即刷新。
     *
     * @param y 目标Y坐标
     * @return 实例自身
     */
    E setY(int y);

    /**
     * @return 可滚动内容的完整宽度
     */
    int getContentWidth();

    /**
     * @return 可滚动内容的完整高度
     */
    int getContentHeight();

    /**
     * @return 元素可见区域的宽度
     */
    int getViewportWidth();

    /**
     * @return 元素可见区域的高度
     */
    int getViewportHeight();


    /**
     * 将元素滚动到指定的(x, y)坐标并刷新。
     * 该方法会进行边界检查，确保不会滚出内容范围。
     *
     * @param x 目标X坐标
     * @param y 目标Y坐标
     * @return 实例自身
     */
    default E scrollTo(int x, int y) {
        int clampedX = clamp(x, 0, getMaxScrollX());
        int clampedY = clamp(y, 0, getMaxScrollY());
        setX(clampedX);
        setY(clampedY);
        return refresh();
    }

    /**
     * 在当前位置的基础上，按给定的偏移量滚动并刷新。
     *
     * @param offsetX 水平偏移量 (正数向右，负数向左)
     * @param offsetY 垂直偏移量 (正数向下，负数向上)
     * @return 实例自身
     */
    default E scrollBy(int offsetX, int offsetY) {
        return scrollTo(currentX() + offsetX, currentY() + offsetY);
    }

    /**
     * 重置滚动位置到 (0, 0) 并刷新
     */
    default E reset() {
        return scrollTo(0, 0);
    }

    /**
     * 滚动到内容顶部
     */
    default E scrollToTop() {
        return scrollTo(currentX(), 0);
    }

    /**
     * 滚动到内容底部
     */
    default E scrollToBottom() {
        return scrollTo(currentX(), getMaxScrollY());
    }

    /**
     * 滚动到内容最左边
     */
    default E scrollToLeft() {
        return scrollTo(0, currentY());
    }

    /**
     * 滚动到内容最右边
     */
    default E scrollToRight() {
        return scrollTo(getMaxScrollX(), currentY());
    }

    /**
     * 按百分比滚动。
     *
     * @param percentX 水平位置百分比 (0.0 到 1.0)
     * @param percentY 垂直位置百分比 (0.0 到 1.0)
     * @return 实例自身
     */
    default E scrollToPercent(double percentX, double percentY) {
        int targetX = (int) (getMaxScrollX() * percentX);
        int targetY = (int) (getMaxScrollY() * percentY);
        return scrollTo(targetX, targetY);
    }

    /**
     * @return 是否可以水平滚动
     */
    default boolean canScrollHorizontally() {
        return getContentWidth() > getViewportWidth();
    }

    /**
     * @return 是否可以垂直滚动
     */
    default boolean canScrollVertically() {
        return getContentHeight() > getViewportHeight();
    }

    /**
     * @return 水平方向上最大可滚动的距离
     */
    default int getMaxScrollX() {
        return Math.max(0, getContentWidth() - getViewportWidth());
    }

    /**
     * @return 垂直方向上最大可滚动的距离
     */
    default int getMaxScrollY() {
        return Math.max(0, getContentHeight() - getViewportHeight());
    }

    /**
     * 一个简单的工具方法，用于将值限制在最小和最大之间
     */
    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}