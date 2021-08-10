package me.maximumpower55.mecha.block.cables;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BasicCableBlock extends CableBlock {
    public BasicCableBlock(Settings settings) {
        super(settings);

    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BasicCableBlockEntity(pos, state);
    }
}
