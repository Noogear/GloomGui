package cn.gloomGui.item.cache;

import cn.gloomGui.Strategy.CacheStrategy;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.OfflinePlayer;

import java.util.*;
import java.util.function.Supplier;

public class ReplacerCache implements CacheStrategy<String, String> {
    private final Supplier<Map<String, String>> supplier;
    private final OfflinePlayer offlinePlayer;
    private WeakHashMap<String, String> cache;

    public ReplacerCache(Set<String> replacerString, OfflinePlayer player) {
        init();
        offlinePlayer = player;
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
        return cache.computeIfAbsent(key, (k) -> ReplacerUtil.apply(k, offlinePlayer));
    }

    @Override
    public String getAndUpdate(String key) {
        return "";
    }


    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }

    @Override
    public ReplacerCache update() {
        cache.putAll(supplier.get());
        return this;
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
