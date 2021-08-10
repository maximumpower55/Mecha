package me.maximumpower55.mecha.block.machines;

import me.maximumpower55.mecha.core.block.BaseBlockEntityProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CompressorMachineBlock extends BaseBlockEntityProvider {
    public CompressorMachineBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CompressorMachineBlockEntity(pos, state);
    }
}
