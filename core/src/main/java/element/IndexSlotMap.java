package element;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class IndexSlotMap {

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

    public boolean add(int slot) {
        if (slotToIndexMap.containsKey(slot)) {
            return false;
        }
        int index = indexToSlotList.size();
        slotToIndexMap.put(slot, index);
        indexToSlotList.add(slot);
        return true;
    }

    public boolean containsSlot(final int slot) {
        return slotToIndexMap.containsKey(slot);
    }
    
    public int getSlot(int index) {
        return indexToSlotList.getInt(index);
    }

    public int getIndex(int slot) {
        int index = slotToIndexMap.get(slot);
        if (index == -1) {
            throw new NoSuchElementException("Slot " + slot + " 不存在于集合中。");
        }
        return index;
    }

    public int size() {
        return indexToSlotList.size();
    }

    public boolean isEmpty() {
        return indexToSlotList.isEmpty();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "IndexSlotMap[size=0, mappings={}]";
        }
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        for (int i = 0; i < size(); i++) {
            sj.add("index=" + i + " -> slot=" + indexToSlotList.getInt(i));
        }
        return "IndexSlotMap[size=" + size() + ", mappings=" + sj + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IndexSlotMap other = (IndexSlotMap) obj;
        return this.indexToSlotList.equals(other.indexToSlotList);
    }

    @Override
    public int hashCode() {
        return indexToSlotList.hashCode();
    }

}
