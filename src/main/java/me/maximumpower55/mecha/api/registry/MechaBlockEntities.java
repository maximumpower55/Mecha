package me.maximumpower55.mecha.api.registry;

import java.util.ArrayList;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class MechaBlockEntities {
    private static final ArrayList<BlockEntityEntry> BLOCK_ENTITIES = new ArrayList<>();

	public static void addBlockEntity(ResourceLocation location, BlockEntityType<?> blockEntityType) {
        BLOCK_ENTITIES.add(new BlockEntityEntry(location, blockEntityType));
	}

    public static void register(String namespace) {
        for (BlockEntityEntry entry : BLOCK_ENTITIES) {
            if(namespace != entry.location.getNamespace()) continue;

            Registry.register(Registry.BLOCK_ENTITY_TYPE, entry.location, entry.blockEntityType);
        }
    }

    private record BlockEntityEntry(ResourceLocation location, BlockEntityType<?> blockEntityType) {}
}
