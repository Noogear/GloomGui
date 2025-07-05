package cn.gloomGui.util;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public final class RegistryUtils {
    private static final ConcurrentHashMap<String, NamespacedKey> KEY_CACHE = new ConcurrentHashMap<>();

    private RegistryUtils() {
    }

    public static @Nullable NamespacedKey toKey(String value) {
        if (value == null) {
            return null;
        }
        return KEY_CACHE.computeIfAbsent(value, k ->
                NamespacedKey.fromString(k.toLowerCase(Locale.ENGLISH))
        );
    }

    public static <T extends Keyed> T fromString(Registry<@NotNull T> registry, String string) {
        if (string == null) {
            return null;
        }
        NamespacedKey namespacedKey = toKey(string);
        return namespacedKey == null ? null : registry.get(namespacedKey);
    }

    public static <T extends Keyed> List<T> fromStringList(Registry<@NotNull T> registry, Collection<String> stringList) {
        return stringList.stream()
                .map(s -> fromString(registry, s))
                .toList();
    }

    public static <T extends Keyed> @Nullable T fromString(RegistryKey<@NotNull T> registryKey, String string) {
        return fromString(RegistryAccess.registryAccess().getRegistry(registryKey), string);
    }

}