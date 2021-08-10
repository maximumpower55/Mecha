package me.maximumpower55.mecha.models.block;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap;
import me.maximumpower55.mecha.core.block.PowerAcceptorBlockEntity;
import net.fabricmc.fabric.api.client.model.BakedModelManagerHelper;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class CableBlockModel implements BakedModel, FabricBakedModel {
    private BakedModel centerModel;
    private final ResourceLocation centerModelId;

    private final Object2ReferenceOpenHashMap<Direction, ResourceLocation> connectionModelIdByDirection;
    private final Object2ReferenceOpenHashMap<Direction, BakedModel> connectionModelByDirection = new Object2ReferenceOpenHashMap<>();

    public CableBlockModel(ResourceLocation centerModelId, Object2ReferenceOpenHashMap<Direction, ResourceLocation> connectionModelIdByDirection) {
        this.centerModelId = centerModelId;
        this.connectionModelIdByDirection = connectionModelIdByDirection;
    }

    private boolean canConnectUp(BlockAndTintGetter blockView, BlockState state, BlockPos pos) {
        if(blockView.getBlockEntity(pos.above()) instanceof PowerAcceptorBlockEntity) return true;

        return false;
    }

    private boolean canConnectNorth(BlockAndTintGetter blockView, BlockState state, BlockPos pos) {
        if(blockView.getBlockEntity(pos.north()) instanceof PowerAcceptorBlockEntity) return true;

        return false;
    }

    private boolean canConnectEast(BlockAndTintGetter blockView, BlockState state, BlockPos pos) {
        if(blockView.getBlockEntity(pos.east()) instanceof PowerAcceptorBlockEntity) return true;

        return false;
    }

    private boolean canConnectDown(BlockAndTintGetter blockView, BlockState state, BlockPos pos) {
        if(blockView.getBlockEntity(pos.below()) instanceof PowerAcceptorBlockEntity) return true;

        return false;
    }

    private boolean canConnectSouth(BlockAndTintGetter blockView, BlockState state, BlockPos pos) {
        if(blockView.getBlockEntity(pos.south()) instanceof PowerAcceptorBlockEntity) return true;

        return false;
    }

    private boolean canConnectWest(BlockAndTintGetter blockView, BlockState state, BlockPos pos) {
        if(blockView.getBlockEntity(pos.west()) instanceof PowerAcceptorBlockEntity) return true;

        return false;
    }

    private void updateCenterModel() {
        centerModel = BakedModelManagerHelper.getModel(Minecraft.getInstance().getModelManager(), centerModelId);
    }

    private void updateUpModel() {
        connectionModelByDirection.putIfAbsent(Direction.UP, BakedModelManagerHelper.getModel(Minecraft.getInstance().getModelManager(), connectionModelIdByDirection.get(Direction.UP)));
    }

    private void updateNorthModel() {
        connectionModelByDirection.putIfAbsent(Direction.NORTH, BakedModelManagerHelper.getModel(Minecraft.getInstance().getModelManager(), connectionModelIdByDirection.get(Direction.NORTH)));
    }

    private void updateEastModel() {
        connectionModelByDirection.putIfAbsent(Direction.EAST, BakedModelManagerHelper.getModel(Minecraft.getInstance().getModelManager(), connectionModelIdByDirection.get(Direction.EAST)));
    }

    private void updateDownModel() {
        connectionModelByDirection.putIfAbsent(Direction.DOWN, BakedModelManagerHelper.getModel(Minecraft.getInstance().getModelManager(), connectionModelIdByDirection.get(Direction.DOWN)));
    }

    private void updateSouthModel() {
        connectionModelByDirection.putIfAbsent(Direction.SOUTH, BakedModelManagerHelper.getModel(Minecraft.getInstance().getModelManager(), connectionModelIdByDirection.get(Direction.SOUTH)));
    }

    private void updateWestModel() {
        connectionModelByDirection.putIfAbsent(Direction.WEST, BakedModelManagerHelper.getModel(Minecraft.getInstance().getModelManager(), connectionModelIdByDirection.get(Direction.WEST)));
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        if(centerModel == null) updateCenterModel();
        updateUpModel();
        updateNorthModel();
        updateEastModel();
        updateDownModel();
        updateSouthModel();
        updateWestModel();

        context.fallbackConsumer().accept(centerModel);

        if(canConnectUp(blockView, state, pos)) {
            context.fallbackConsumer().accept(connectionModelByDirection.get(Direction.UP));
        }

        if(canConnectNorth(blockView, state, pos)) {
            context.fallbackConsumer().accept(connectionModelByDirection.get(Direction.NORTH));
        }

        if(canConnectEast(blockView, state, pos)) {
            context.fallbackConsumer().accept(connectionModelByDirection.get(Direction.EAST));
        }

        if(canConnectDown(blockView, state, pos)) {
            context.fallbackConsumer().accept(connectionModelByDirection.get(Direction.DOWN));
        }

        if(canConnectSouth(blockView, state, pos)) {
            context.fallbackConsumer().accept(connectionModelByDirection.get(Direction.SOUTH));
        }

        if(canConnectWest(blockView, state, pos)) {
            context.fallbackConsumer().accept(connectionModelByDirection.get(Direction.WEST));
        }
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        if(centerModel == null) updateCenterModel();

        context.fallbackConsumer().accept(centerModel);
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction face, Random random) {
        return null;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return true;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getSprite() {
        return centerModel.getSprite();
    }

    @Override
    public ItemTransforms getTransforms() {
        if(centerModel == null) updateCenterModel();

        return centerModel.getTransforms();
    }

    @Override
    public ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }
}
