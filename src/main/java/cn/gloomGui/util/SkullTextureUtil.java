package cn.gloomGui.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SkullTextureUtil {

    protected static final Cache<String, PlayerProfile> CACHE = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.HOURS).build();
    protected static final Executor EXECUTOR = CompletableFuture.completedFuture(null).defaultExecutor();
    private static final String TEXTURE_URL = "http://textures.minecraft.net/texture/";
    private static final UUID EMPTY_ID = new UUID(0, 0);
    private static final PlayerProfile EMPTY = Bukkit.createProfile(EMPTY_ID, "null");
    private static final Gson GSON = new Gson();

    private SkullTextureUtil() {
    }

    @NotNull
    public static PlayerProfile getProfileByString(@Nullable String value) {
        if (value == null || value.isEmpty()) {
            return EMPTY;
        }
        return caching(value, () -> {
            int length = value.length();
            if (length < 32) {
                return profileFromName(value);
            } else if (length == 32) {
                return profileFromUUID(UUID.fromString(new StringBuilder(value)
                        .insert(20, '-')
                        .insert(16, '-')
                        .insert(12, '-')
                        .insert(8, '-')
                        .toString()));
            } else if (length == 36) {
                return profileFromUUID(UUID.fromString(value));
            } else if (length == 64) {
                return profileFromUrl(TEXTURE_URL + value);
            } else if (value.startsWith("http")) {
                return profileFromUrl(value);
            } else {
                return profileFromBase64(value);
            }
        });
    }

    public static boolean isEmpty(PlayerProfile profile) {
        if (profile == null) {
            return true;
        }
        return profile.equals(EMPTY);
    }

    @NotNull
    private static PlayerProfile profileFromBase64(String value) {
        String decoded = new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
        JsonObject json = GSON.fromJson(decoded, JsonObject.class);
        String url = json.getAsJsonObject("textures").getAsJsonObject("SKIN").get("url").getAsString();
        return profileFromUrl(url);
    }

    @NotNull
    private static PlayerProfile profileFromUrl(String value) {
        try {
            URL url = URI.create(value).toURL();
            PlayerProfile newProfile = Bukkit.createProfile(EMPTY_ID, "");
            newProfile.getTextures().setSkin(url);
            return newProfile;
        } catch (MalformedURLException e) {
            return EMPTY;
        }
    }

    @NotNull
    private static PlayerProfile profileFromUUID(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            return player.getPlayerProfile();
        } else {
            return Bukkit.createProfile(uuid);
        }
    }

    @NotNull
    private static PlayerProfile profileFromName(String value) {
        Player player = Bukkit.getPlayer(value);
        if (player != null) {
            return player.getPlayerProfile();
        } else {
            return Bukkit.createProfile(value);
        }
    }

    public static ItemStack setProfile(@NotNull ItemStack stack, @Nullable PlayerProfile profile) {
        if (stack.getItemMeta() instanceof SkullMeta skullMeta) {
            skullMeta.setPlayerProfile(profile);
            stack.setItemMeta(skullMeta);
        }
        return stack;
    }

    @NotNull
    protected static PlayerProfile caching(@NotNull String key, @NotNull Supplier<PlayerProfile> supplier) {
        PlayerProfile value = CACHE.getIfPresent(key);
        if (value == null) {
            try {
                value = supplier.get();
            } catch (Throwable ignored) {
            }
            if (value == null) {
                value = EMPTY;
            }
            CACHE.put(key, value);
        }
        return value;
    }

    @NotNull
    public static CompletableFuture<ItemStack> itemAsync(@NotNull ItemStack stack, @NotNull String key) {
        return itemAsync(stack, key, EXECUTOR);
    }

    @NotNull
    public static CompletableFuture<ItemStack> itemAsync(@NotNull ItemStack stack, @NotNull String key, @NotNull Executor executor) {
        PlayerProfile profile = CACHE.getIfPresent(key);
        if (profile != null) {
            return CompletableFuture.completedFuture(setProfile(stack, profile));
        }
        return CompletableFuture.supplyAsync(() -> setProfile(stack, getProfileByString(key)), executor);
    }


}
