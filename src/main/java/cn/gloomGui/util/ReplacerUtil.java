package cn.gloomGui.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class ReplacerUtil {
    private static final Pattern REPLACE_PATTERN = Pattern.compile("%[^%]+?%");

    public static boolean contains(String string) {
        return REPLACE_PATTERN.matcher(string).find();
    }

    public static String apply(String string, Player player) {
        return PlaceholderAPI.setPlaceholders(player, string);
    }

    public static String apply(String string, OfflinePlayer player) {
        return PlaceholderAPI.setPlaceholders(player, string);
    }

}
