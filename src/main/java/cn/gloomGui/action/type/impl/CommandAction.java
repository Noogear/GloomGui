package cn.gloomGui.action.type.impl;

import cn.gloomGui.action.type.NormalAction;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.entity.Player;

import java.util.function.Consumer;
import java.util.function.Function;

public class CommandAction implements NormalAction {
    private Function<Player, String> command;

    @Override
    public void run(Player player, Consumer<Boolean> callback) {
        player.performCommand(command.apply(player));
        callback.accept(true);
    }

    @Override
    public boolean initFromString(String string) {
        if (string.isEmpty()) {
            return false;
        }
        if (ReplacerUtil.contains(string)) {
            command = (player) -> ReplacerUtil.apply(string, player);
        } else {
            command = (player) -> string;
        }
        return true;
    }
}
