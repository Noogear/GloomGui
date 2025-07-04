package cn.gloomGui.object.stringReplacer;

import org.bukkit.OfflinePlayer;

@FunctionalInterface
public interface IntReplacer {
    int get(OfflinePlayer player);
}
