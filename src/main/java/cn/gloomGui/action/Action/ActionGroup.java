package cn.gloomGui.action.Action;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class ActionGroup implements Action {
    private final List<Action> actions;

    public ActionGroup(List<Action> actions) {
        this.actions = actions;
    }

    public ActionGroup() {
        this(new ArrayList<>());
    }

    public ActionGroup addAction(Action... action) {
        actions.addAll(List.of(action));
        return this;
    }

    @Override
    public void run(Player player, Consumer<Boolean> callback) {
        RunContext context = new RunContext(player, new LinkedList<>(actions));
        context.start();
    }

    private record RunContext(Player player, Queue<Action> queue) {

        void start() {
            runNext();
        }
    
        private void runNext() {
            if (queue.isEmpty()) {
                return;
            }
            Action nextAction = queue.poll();
            nextAction.run(player, success -> {
                if (!success) {
                    queue.clear();
                    return;
                }
                runNext();
            });
        }
    }
}
