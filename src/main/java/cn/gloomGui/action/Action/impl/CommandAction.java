package cn.gloomGui.action.Action.impl;

import cn.gloomGui.action.Action.NormalAction;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.entity.Player;

import java.util.function.Consumer;
import java.util.function.Function;

public class CommandAction implements NormalAction {
    Function<Player, String> command;

    @Override
    public void run(Player player, Consumer<Boolean> callback) {
        player.performCommand(command.apply(player));
        callback.accept(true);
    }

    @Override
    public void initFromString(String string) {
        if (ReplacerUtil.contains(string)) {
            command = (player) -> ReplacerUtil.apply(string, player);
        } else {
            command = (player) -> string;
        }
    }
}
