package cn.gloomGui.strategy;

import java.util.Optional;
import java.util.Set;

public interface CacheStrategy<K, V> {

    void init();

    void destroy();

    V get(K key);

    V getAndUpdate(K key);

    void put(K key, V value);

    CacheStrategy<K, V> update();

    void remove(K key);

    default Optional<V> safeGet(K key) {
        return Optional.ofNullable(get(key));
    }

    int size();

    boolean containsKey(K key);

    Set<K> keySet();

}
