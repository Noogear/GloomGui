package element;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.*;

public final class IndexSlotMap {

    private final IntList indexToSlotList;
    private final Int2IntMap slotToIndexMap;

    public IndexSlotMap() {
        this.indexToSlotList = new IntArrayList();
        this.slotToIndexMap = new Int2IntOpenHashMap();
        this.slotToIndexMap.defaultReturnValue(-1);
    }

    public IndexSlotMap(int initialCapacity) {
        this.indexToSlotList = new IntArrayList(initialCapacity);
        this.slotToIndexMap = new Int2IntOpenHashMap(initialCapacity);
        this.slotToIndexMap.defaultReturnValue(-1);
    }

    public IndexSlotMap(int[] initialSlots) {
        if (initialSlots == null) {
            throw new IllegalArgumentException("Initial slots array cannot be null.");
        }
        if (initialSlots.length == 0) {
            this.indexToSlotList = new IntArrayList();
            this.slotToIndexMap = new Int2IntOpenHashMap();
            this.slotToIndexMap.defaultReturnValue(-1);
            return;
        }
        final int[] uniqueSortedSlots = Arrays.stream(initialSlots).distinct().sorted().toArray();

        this.indexToSlotList = new IntArrayList(uniqueSortedSlots);
        this.slotToIndexMap = new Int2IntOpenHashMap(uniqueSortedSlots.length);
        this.slotToIndexMap.defaultReturnValue(-1);

        for (int i = 0; i < uniqueSortedSlots.length; i++) {
            this.slotToIndexMap.put(uniqueSortedSlots[i], i);
        }
    }

    public IndexSlotMap(Collection<Integer> initialSlots) {
        this(initialSlots == null ? new int[0] : initialSlots.stream().mapToInt(Integer::intValue).toArray());
    }

    public static IndexSlotMap fromSlots(int... slots) {
        return new IndexSlotMap(slots);
    }

    public int[] getAllSlot() {
        return indexToSlotList.toIntArray();
    }

    public boolean add(final int slot) {
        if (containsSlot(slot)) {
            return false;
        }
        int index = Collections.binarySearch(this.indexToSlotList, slot);
        if (index < 0) {
            index = -index - 1;
        }
        add(index, slot);
        return true;
    }

    public void add(final int index, final int slot) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        if (containsSlot(slot)) {
            throw new IllegalArgumentException("Slot " + slot + " already exists.");
        }
        this.indexToSlotList.add(index, slot);
        rebuildIndexMappings(index);
    }

    public int remove(final int index) {
        final int removedSlot = this.indexToSlotList.removeInt(index);
        this.slotToIndexMap.remove(removedSlot);
        rebuildIndexMappings(index);
        return removedSlot;
    }

    public boolean removeSlot(final int slot) {
        final int index = this.slotToIndexMap.get(slot);
        if (index == -1) {
            return false; // Slot 不存在
        }
        remove(index);
        return true;
    }

    private void rebuildIndexMappings(int fromIndex) {
        final int currentSize = this.indexToSlotList.size();
        for (int i = fromIndex; i < currentSize; i++) {
            this.slotToIndexMap.put(this.indexToSlotList.getInt(i), i);
        }
    }

    public int getSlot(final int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        return indexToSlotList.getInt(index);
    }

    public int getIndex(final int slot) {
        final int index = slotToIndexMap.get(slot);
        if (index == -1) {
            throw new NoSuchElementException("Slot " + slot + " does not exist in the map.");
        }
        return index;
    }

    public boolean containsSlot(final int slot) {
        return slotToIndexMap.containsKey(slot);
    }

    public int getFirstSlot() {
        return indexToSlotList.getFirst();
    }

    public int getLastSlot() {
        return indexToSlotList.getLast();
    }

    public int size() {
        return indexToSlotList.size();
    }

    public boolean isEmpty() {
        return indexToSlotList.isEmpty();
    }

    @Override
    public int hashCode() {
        return indexToSlotList.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final IndexSlotMap that = (IndexSlotMap) o;
        return this.indexToSlotList.equals(that.indexToSlotList);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "IndexSlotMap[size=0, mappings={}]";
        }
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        for (int i = 0; i < size(); i++) {
            sj.add(i + "->" + indexToSlotList.getInt(i));
        }
        return "IndexSlotMap[size=" + size() + ", mappings=" + sj + "]";
    }
}