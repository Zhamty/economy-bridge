package su.nightexpress.economybridge.item.handler.impl;

import emanondev.itemedit.ItemEdit;
import emanondev.itemedit.storage.ServerStorage;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nightexpress.economybridge.item.ItemPlugins;
import su.nightexpress.economybridge.item.handler.AbstractItemHandler;

import java.util.Objects;

public class ItemEditHandler extends AbstractItemHandler {

    @Override
    @NotNull
    public String getName() {
        return ItemPlugins.ITEM_EDIT;
    }

    @Override
    @Nullable
    public ItemStack createItem(@NotNull String itemId) {
        return ItemEdit.get().getServerStorage().getItem(itemId);
    }

    @Override
    public boolean canHandle(@NotNull ItemStack item) {
        ServerStorage serverStorage = ItemEdit.get().getServerStorage();
        for (String itemID : serverStorage.getIds()) {
            if (Objects.requireNonNull(serverStorage.getItem(itemID)).isSimilar(item)) return true;
        }
        return false;
    }

    @Override
    public boolean isValidId(@NotNull String itemId) {
        return ItemEdit.get().getServerStorage().getIds().contains(itemId);
    }

    @Override
    @Nullable
    public String getItemId(@NotNull ItemStack item) {
        if (item.getType().isAir()) return null;

        ServerStorage serverStorage = ItemEdit.get().getServerStorage();
        for (String itemID : serverStorage.getIds()) {
            if (Objects.requireNonNull(serverStorage.getItem(itemID)).isSimilar(item)) return itemID;
        }
        return null;
    }
}
