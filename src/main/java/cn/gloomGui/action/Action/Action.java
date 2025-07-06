package cn.gloomGui.action.Action;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

@FunctionalInterface
public interface Action {

    void run(Player player, Consumer<Boolean> callback);

}
