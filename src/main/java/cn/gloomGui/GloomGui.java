package cn.gloomGui;

import cn.gloomGui.util.SchedulerUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class GloomGui extends JavaPlugin {
    private final GloomGui instance;
    private final JavaPlugin plugin;
    private final SchedulerUtil scheduler;

    public GloomGui(JavaPlugin plugin) {
        this.plugin = plugin;

        instance = this;

        scheduler = new SchedulerUtil(plugin);
    }

    public GloomGui getInstance() {
        return instance;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public SchedulerUtil getScheduler() {
        return scheduler;
    }
}
