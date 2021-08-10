package me.maximumpower55.mecha.api;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public final class MechaBlocks {
    private static final ArrayList<BlockEntry> BLOCK_WITHOUT_ITEMS = new ArrayList<>();
    private static final ArrayList<BlockWithItemEntry> BLOCK_WITH_ITEMS = new ArrayList<>();

	public static void addBlock(ResourceLocation location, Block block) {
        BLOCK_WITHOUT_ITEMS.add(new BlockEntry(location, block));
	}

	public static void addBlockWithItem(ResourceLocation location, Block block, ItemLike item) {
        BLOCK_WITH_ITEMS.add(new BlockWithItemEntry(location, block, item));
	}

    public static void register(String namespace) {
        for (BlockEntry entry : BLOCK_WITHOUT_ITEMS) {
            if(namespace != entry.location.getNamespace()) continue;

            Registry.register(Registry.BLOCK, entry.location, entry.block);
        }

        for (BlockWithItemEntry entry : BLOCK_WITH_ITEMS) {
            if(namespace != entry.location.getNamespace()) continue;

            Registry.register(Registry.BLOCK, entry.location, entry.block);
            Registry.register(Registry.ITEM, entry.location, entry.item.asItem());
        }
    }

    private record BlockEntry(ResourceLocation location, Block block) {}
    private record BlockWithItemEntry(ResourceLocation location, Block block, ItemLike item) {}
}
