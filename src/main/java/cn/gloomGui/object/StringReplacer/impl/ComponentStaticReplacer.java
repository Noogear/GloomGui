package cn.gloomGui.object.StringReplacer.impl;

import cn.gloomGui.object.StringReplacer.ReplacerHandler;
import cn.gloomGui.util.AdventureUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;

public class ComponentStaticReplacer implements ReplacerHandler<Component> {
    private final Component component;

    public ComponentStaticReplacer(String string) {
        this.component = AdventureUtil.deserialize(string);
    }

    public ComponentStaticReplacer(Component component) {
        this.component = component;
    }

    @Override
    public Component get(OfflinePlayer player) {
        return component;
    }

}
