package me.maximumpower55.mecha;

import static me.maximumpower55.mecha.api.utils.ResourceUtils.prefixPath;

import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class ModModels {
    public static final ResourceLocation COMPRESSOR_PISTON = load(prefixPath(MechaMod.id("compressor_piston"), "block"));

    public static final ResourceLocation BASIC_CABLE_BLOCK_CENTER = load(prefixPath(MechaMod.id("basic_cable_block_center"), "block"));

    public static final Object2ReferenceOpenHashMap<Direction, ResourceLocation> BASIC_CABLE_BLOCK_CONNECTIONS = loadBasicCableBlockConnections();

    private static Object2ReferenceOpenHashMap<Direction, ResourceLocation> loadBasicCableBlockConnections() {
        Object2ReferenceOpenHashMap<Direction, ResourceLocation> connectionModelIdByDirection = new Object2ReferenceOpenHashMap<>();

        connectionModelIdByDirection.put(Direction.UP, load(prefixPath(MechaMod.id("basic_cable_block_up"), "block")));
        connectionModelIdByDirection.put(Direction.NORTH, load(prefixPath(MechaMod.id("basic_cable_block_north"), "block")));
        connectionModelIdByDirection.put(Direction.EAST, load(prefixPath(MechaMod.id("basic_cable_block_east"), "block")));
        connectionModelIdByDirection.put(Direction.DOWN, load(prefixPath(MechaMod.id("basic_cable_block_down"), "block")));
        connectionModelIdByDirection.put(Direction.SOUTH, load(prefixPath(MechaMod.id("basic_cable_block_south"), "block")));
        connectionModelIdByDirection.put(Direction.WEST, load(prefixPath(MechaMod.id("basic_cable_block_west"), "block")));

        return connectionModelIdByDirection;
    };

    public static void load() {};

    private static ResourceLocation load(ResourceLocation id) {
        ModelLoadingRegistry.INSTANCE.registerModelProvider((resourceManager, consumer) -> {
            consumer.accept(id);
        });

        return id;
    }
}
