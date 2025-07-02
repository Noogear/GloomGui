package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.ReplacerUtil;
import cn.gloomGui.util.SkullTextureUtil;
import com.destroystokyo.paper.profile.PlayerProfile;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProfileModifier implements ItemMetaModifier {
    private String profile;
    private boolean usePlaceholder;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        if (profile != null && meta instanceof SkullMeta skullMeta) {
            PlayerProfile playerProfile;
            if (usePlaceholder) {
                playerProfile = SkullTextureUtil.getPlayerProfile(PlaceholderAPI.setPlaceholders(player, profile));
            } else {
                playerProfile = SkullTextureUtil.getPlayerProfile(profile);
            }
            if (playerProfile == null) {
                return meta;
            }
            skullMeta.setPlayerProfile(playerProfile);
            return skullMeta;
        } else {
            return meta;
        }
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        profile = string;
        usePlaceholder = ReplacerUtil.contains(string);
        return true;
    }
}
