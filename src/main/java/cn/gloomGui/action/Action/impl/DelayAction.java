package cn.gloomGui.action.Action.impl;

import cn.gloomGui.action.Action.NormalAction;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.SchedulerUtil;
import cn.gloomGui.util.StringUtil;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class DelayAction implements NormalAction {
    private Function<Player, Long> delay;

    @Override
    public void run(Player player, Consumer<Boolean> callback) {
        Long delayTick = this.delay.apply(player);
        if (delayTick == null || delayTick == 0L) {
            callback.accept(false);
            return;
        }
        SchedulerUtil.get().runOnEntityLater(player, () -> callback.accept(true), null, delayTick);
    }

    @Override
    public boolean initFromString(String string) {
        if (string.isEmpty()) {
            return false;
        }
        if (ReplacerUtil.contains(string)) {
            delay = (player) -> StringUtil.parseNumber(ReplacerUtil.apply(string, player)).map(BigDecimal::longValue).orElse(0L);
            return true;
        } else {
            Optional<Long> optionalLong = StringUtil.parseNumber(string).map(BigDecimal::longValue);
            if (optionalLong.isPresent()) {
                Long value = optionalLong.get();
                delay = (player) -> value;
                return true;
            } else {
                return false;
            }
        }
    }
}
