package cn.gloomGui.object.stringReplacer;

import org.bukkit.OfflinePlayer;

@FunctionalInterface
public interface BooleanReplacer {
    boolean get(OfflinePlayer player);
}
