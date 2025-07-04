package cn.gloomGui.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class AdventureUtil {
    private AdventureUtil() {
    }

    public static @NotNull Component deserialize(@NotNull String string) {
        return MiniMessage.miniMessage().deserialize(string);
    }

    public static @NotNull List<Component> deserialize(@NotNull List<String> stringList) {
        return stringList.stream().map(AdventureUtil::deserialize).toList();
    }


}
