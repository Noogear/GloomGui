package cn.gloomGui.cache;

import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class ReplacerTemporaryCache implements CacheStrategy<String, String> {
    private final Supplier<Map<String, String>> supplier;
    private WeakHashMap<String, String> cache;

    public ReplacerTemporaryCache(Set<String> replacerString, OfflinePlayer player) {
        init();
        supplier = () -> {
            Map<String, String> map = new HashMap<>();
            for (String string : replacerString) {
                map.put(string, ReplacerUtil.apply(string, player));
            }
            return map;
        };
    }

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
    public boolean update() {
        if (supplier == null) {
            return false;
        }
        cache.putAll(supplier.get());
        return true;
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
