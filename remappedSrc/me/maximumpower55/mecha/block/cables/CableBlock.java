package me.maximumpower55.mecha.block.cables;

import me.maximumpower55.mecha.core.block.BaseBlockEntityProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class CableBlock extends BaseBlockEntityProvider {
    protected CableBlock(Settings settings) {
        super(settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public RenderShape getRenderType(BlockState state) {
        return RenderShape.MODEL;
    }

	@Override
    @Environment(EnvType.CLIENT)
	public VoxelShape getOutlineShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext shapeContext) {
        float size = 6f;

		return Block.box(size, size, size, 16d - size, 16d - size, 16d - size);
	}
}
