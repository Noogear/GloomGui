package cn.gloomGui.object.stringReplacer.impl;

import cn.gloomGui.cache.ReplacerCache;
import cn.gloomGui.object.stringReplacer.ReplacerStrategy;
import cn.gloomGui.util.AdventureUtil;
import net.kyori.adventure.text.Component;

public class ComponentDynamicReplacer implements ReplacerStrategy<Component> {
    private final String string;

    public ComponentDynamicReplacer(String string) {
        this.string = string;
    }

    @Override
    public Component get(ReplacerCache replacerCache) {
        return AdventureUtil.deserialize(replacerCache.get(string));
    }
}
