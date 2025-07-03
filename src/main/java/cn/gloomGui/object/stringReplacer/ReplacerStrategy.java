package cn.gloomGui.object.stringReplacer;

import org.bukkit.OfflinePlayer;

@FunctionalInterface
public interface ReplacerStrategy<T> {
    T get(OfflinePlayer player);
}
