package cn.gloomGui.util;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryUtils {
    private static final Map<Class<?>, Map<String, ?>> CACHE = new ConcurrentHashMap<>();

    public static NamespacedKey toKey(String value) {
        return NamespacedKey.fromString(value.toLowerCase(Locale.ENGLISH));
    }

    public static <T extends Keyed> T fromString(Registry<@NotNull T> registry, String string) {
        NamespacedKey namespacedKey = toKey(string);
        if (namespacedKey == null) {
            return null;
        }
        return registry.get(namespacedKey);
    }

    public static <T extends Keyed> List<T> fromStringList(Registry<@NotNull T> registry, Collection<String> stringList) {
        return stringList.stream().map(string -> RegistryUtils.fromString(registry, string)).toList();
    }


}
