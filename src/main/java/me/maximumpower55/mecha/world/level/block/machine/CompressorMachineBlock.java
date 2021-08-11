package me.maximumpower55.mecha.world.level.block.machine;

import me.maximumpower55.mecha.api.block.BaseBlockEntityProvider;
import me.maximumpower55.mecha.world.level.block.entity.machine.CompressorMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CompressorMachineBlock extends BaseBlockEntityProvider {
    public CompressorMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CompressorMachineBlockEntity(pos, state);
    }
}
