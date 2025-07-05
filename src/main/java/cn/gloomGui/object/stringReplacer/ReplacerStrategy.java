package cn.gloomGui.object.stringReplacer;

import cn.gloomGui.cache.ReplacerCache;

@FunctionalInterface
public interface ReplacerStrategy<T> {

    T get(ReplacerCache replacerCache);
}
