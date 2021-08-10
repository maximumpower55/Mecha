package me.maximumpower55.mecha;

import me.maximumpower55.mecha.block.machines.renderers.CompressorMachineBlockEntityRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

public class ModBlockEntityRenderers {
    public static void register() {
        BlockEntityRendererRegistry.INSTANCE.register(ModBlockEntities.COMPRESSOR_MACHINE_BLOCK_ENTITY, CompressorMachineBlockEntityRenderer::new);
    }
}
