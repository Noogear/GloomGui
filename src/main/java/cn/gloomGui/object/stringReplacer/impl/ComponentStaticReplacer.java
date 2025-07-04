package cn.gloomGui.object.stringReplacer.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.object.stringReplacer.ReplacerStrategy;
import cn.gloomGui.util.AdventureUtil;
import net.kyori.adventure.text.Component;

public class ComponentStaticReplacer implements ReplacerStrategy<Component> {
    private final Component component;

    public ComponentStaticReplacer(String string) {
        this.component = AdventureUtil.deserialize(string);
    }

    public ComponentStaticReplacer(Component component) {
        this.component = component;
    }


    @Override
    public Component get(ReplacerCache replacerCache) {
        return component;
    }
}
