package cn.gloomGui.util;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.concurrent.TimeUnit;

public class PlatformScheduler {
    public static PlatformScheduler instance;
    private final IScheduler scheduler;

    public PlatformScheduler(JavaPlugin plugin) {
        instance = this;

        IScheduler iScheduler;
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            iScheduler = new FoliaScheduler(plugin);
        } catch (ClassNotFoundException e) {
            iScheduler = new CommonScheduler(plugin);
        }
        this.scheduler = iScheduler;
    }

    public static IScheduler get() {
        return instance.scheduler;
    }

    public interface IScheduler {

        void cancelAll();

        /**
         * Run a task later
         *
         * @param task  The task to run
         * @param delay The delay in ticks (20 ticks = 1 second)
         */
        void runTaskLater(Runnable task, long delay);

        /**
         * Run a task
         *
         * @param task The task to run
         */
        void runTask(Runnable task);

        /**
         * Run a task later asynchronously
         *
         * @param task  The task to run
         * @param delay The delay in milliseconds
         */
        void runTaskLaterAsync(Runnable task, long delay);

        /**
         * Run a task asynchronously
         *
         * @param task The task to run
         */
        void runTaskAsync(Runnable task);

    }

    private static class CommonScheduler implements IScheduler {
        private final JavaPlugin plugin;
        private final BukkitScheduler scheduler;

        public CommonScheduler(JavaPlugin plugin) {
            this.plugin = plugin;
            scheduler = plugin.getServer().getScheduler();
        }

        @Override
        public void cancelAll() {
            scheduler.cancelTasks(plugin);
        }

        @Override
        public void runTaskLater(Runnable task, long delay) {
            if (delay <= 0) {
                runTask(task);
                return;
            }
            scheduler.runTaskLater(plugin, task, delay);
        }

        @Override
        public void runTask(Runnable task) {
            scheduler.runTask(plugin, task);
        }


        @Override
        public void runTaskLaterAsync(Runnable task, long delay) {
            if (delay <= 0) {
                runTaskAsync(task);
                return;
            }
            scheduler.runTaskLaterAsynchronously(plugin, task, delay);
        }

        @Override
        public void runTaskAsync(Runnable task) {
            scheduler.runTaskAsynchronously(plugin, task);
        }
    }

    private static class FoliaScheduler implements IScheduler {
        private final JavaPlugin plugin;
        private final GlobalRegionScheduler scheduler;
        private final AsyncScheduler asyncScheduler;

        public FoliaScheduler(JavaPlugin plugin) {
            this.plugin = plugin;
            this.asyncScheduler = plugin.getServer().getAsyncScheduler();
            scheduler = plugin.getServer().getGlobalRegionScheduler();
        }

        @Override
        public void cancelAll() {
            scheduler.cancelTasks(plugin);
        }

        @Override
        public void runTaskLater(Runnable task, long delay) {
            if (delay <= 0) {
                runTask(task);
                return;
            }
            scheduler.runDelayed(plugin, (plugin) -> task.run(), delay);
        }

        @Override
        public void runTask(Runnable task) {
            scheduler.run(plugin, (plugin) -> task.run());
        }

        @Override
        public void runTaskLaterAsync(Runnable task, long delay) {
            if (delay <= 0) {
                runTaskAsync(task);
                return;
            }
            asyncScheduler.runDelayed(plugin, (plugin) -> task.run(), delay * 50, TimeUnit.MILLISECONDS);
        }

        @Override
        public void runTaskAsync(Runnable task) {
            asyncScheduler.runNow(plugin, (plugin) -> task.run());
        }
    }
}
