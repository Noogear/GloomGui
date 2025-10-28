package element;

public interface IndexContainer<I> extends Container<I>{

    IndexSlotMap getIndexSlotMap();

    @Override
    default int[] slots(){
       return getIndexSlotMap().getAllSlot();
    };

    @Override
    default int size(){
        return getIndexSlotMap().size();
    }

    @Override
    default boolean contains(int slot) {
        return getIndexSlotMap().containsSlot(slot);
    };



}
