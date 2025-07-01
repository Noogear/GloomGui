package cn.gloomGui.item.modifier.impl;

import cn.gloomGui.item.modifier.ItemMetaModifier;
import cn.gloomGui.object.StringReplacer.ReplacerHandler;
import cn.gloomGui.object.StringReplacer.impl.KeyDynamicReplacer;
import cn.gloomGui.object.StringReplacer.impl.KeyStaticReplacer;
import cn.gloomGui.util.ObjectUtil;
import cn.gloomGui.util.RegistryUtils;
import cn.gloomGui.util.ReplacerUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemModelModifier implements ItemMetaModifier {
    private ReplacerHandler<NamespacedKey> itemModel;

    @Override
    public @NotNull ItemMeta modifyMeta(@NotNull ItemMeta meta, @Nullable OfflinePlayer player) {
        if (itemModel == null) {
            return meta;
        }
        meta.setItemModel(itemModel.get(player));
        return meta;
    }

    @Override
    public boolean loadFromObject(Object value) {
        if (value == null) {
            return false;
        }
        String string = ObjectUtil.toString(value);
        if (ReplacerUtil.contains(string)) {
            this.itemModel = new KeyDynamicReplacer(string);
        } else {
            NamespacedKey key = RegistryUtils.toKey(string);
            if (key == null) {
                return false;
            }
            this.itemModel = new KeyStaticReplacer(key);
        }
        return true;
    }

}
