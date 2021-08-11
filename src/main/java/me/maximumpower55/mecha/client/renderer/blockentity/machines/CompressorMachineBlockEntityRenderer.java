package me.maximumpower55.mecha.client.renderer.blockentity.machines;

import com.mojang.blaze3d.vertex.PoseStack;

import me.maximumpower55.mecha.ModModels;
import me.maximumpower55.mecha.api.utils.MechaEvents;
import me.maximumpower55.mecha.api.utils.ModelUtils;
import me.maximumpower55.mecha.world.level.block.entity.machine.CompressorMachineBlockEntity;
import net.fabricmc.fabric.api.client.model.BakedModelManagerHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;

public class CompressorMachineBlockEntityRenderer implements BlockEntityRenderer<CompressorMachineBlockEntity> {
    private BakedModel pistonModel;

    public CompressorMachineBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        MechaEvents.RELOAD_MODELS.register((loader, resourceManager, profiler) -> {
            onModelReload();
        });
    }

    @Override
    public void render(CompressorMachineBlockEntity blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        if(pistonModel == null) pistonModel = getPistonModel();

        if(pistonModel == null) return;

        float pistonY = 0.6f - blockEntity.getCompressorProgress() * 0.005f;

        matrices.pushPose();

        matrices.translate(0.0, pistonY, 0.0);

        int pistonModelLight = LevelRenderer.getLightColor(blockEntity.getLevel(), blockEntity.getBlockPos().above());
        ModelUtils.renderModel(vertexConsumers.getBuffer(RenderType.solid()), matrices, pistonModel, pistonModelLight, OverlayTexture.NO_OVERLAY, Direction.NORTH);

        matrices.popPose();
    }

    private BakedModel getPistonModel() {
        return BakedModelManagerHelper.getModel(Minecraft.getInstance().getModelManager(), ModModels.COMPRESSOR_PISTON);
    }

    private void onModelReload() {
        pistonModel = null;
    }
}
