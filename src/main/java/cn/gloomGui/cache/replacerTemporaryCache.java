package cn.gloomGui.cache;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Function;

public class replacerTemporaryCache implements cacheStrategy<String, String> {
    private WeakHashMap<String, String> cache;

    @Override
    public void init() {
        cache = new WeakHashMap<>();
        cache.clear();
    }

    @Override
    public void destroy() {
        cache.clear();
        cache = null;
    }

    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public String getOrDefault(String key, String defaultValue) {
        return cache.getOrDefault(key, defaultValue);
    }

    @Override
    public String computeIfAbsent(String key, Function<String, String> loader) {
        return cache.computeIfAbsent(key, loader);
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    @Override
    public Set<String> keySet() {
        return Collections.unmodifiableSet(cache.keySet());
    }
}
