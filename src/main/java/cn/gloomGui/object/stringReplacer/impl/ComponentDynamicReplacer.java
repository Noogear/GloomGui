package cn.gloomGui.object.stringReplacer.impl;

import cn.gloomGui.object.stringReplacer.ReplacerStrategy;
import cn.gloomGui.util.AdventureUtil;
import cn.gloomGui.util.ReplacerUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;

public class ComponentDynamicReplacer implements ReplacerStrategy<Component> {
    private final String string;

    public ComponentDynamicReplacer(String string) {
        this.string = string;
    }

    @Override
    public Component get(OfflinePlayer player) {
        return AdventureUtil.deserialize(ReplacerUtil.apply(string, player));
    }
}
