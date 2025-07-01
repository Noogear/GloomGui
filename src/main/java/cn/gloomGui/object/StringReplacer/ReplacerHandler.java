package cn.gloomGui.object.StringReplacer;

import org.bukkit.OfflinePlayer;

public interface ReplacerHandler<T> {
    T get(OfflinePlayer player);
}
