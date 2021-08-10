package me.maximumpower55.mecha.api;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public final class MechaItems {
    private static final ArrayList<ItemEntry> ITEMS = new ArrayList<>();

	public static void addItem(ResourceLocation location, ItemLike item) {
        ITEMS.add(new ItemEntry(location, item));
	}

    public static void register(String namespace) {
        for (ItemEntry entry : ITEMS) {
            if(namespace != entry.location.getNamespace()) continue;

            Registry.register(Registry.ITEM, entry.location, entry.item.asItem());
        }
    }

    private record ItemEntry(ResourceLocation location, ItemLike item) {}
}
