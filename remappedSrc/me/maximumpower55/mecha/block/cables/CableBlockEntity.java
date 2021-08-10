package me.maximumpower55.mecha.block.cables;

import me.maximumpower55.mecha.core.block.PowerAcceptorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class CableBlockEntity extends PowerAcceptorBlockEntity implements BlockEntityTicker<CableBlockEntity> {
	protected CableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, double energyCapacity, double maxEnergyInput, double maxEnergyOutput) {
		super(type, pos, state, energyCapacity, maxEnergyInput, maxEnergyOutput);
	}
}
