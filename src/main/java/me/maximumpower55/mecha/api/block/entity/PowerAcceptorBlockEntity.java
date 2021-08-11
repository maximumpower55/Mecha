package me.maximumpower55.mecha.api.block.entity;

import dev.technici4n.fasttransferlib.api.Simulation;
import dev.technici4n.fasttransferlib.api.energy.EnergyIo;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PowerAcceptorBlockEntity extends BlockEntity implements EnergyIo {
    protected double storedEnergy;
    protected final double energyCapacity;
    protected final double maxEnergyInput;
    protected final double maxEnergyOutput;

    public PowerAcceptorBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState, double energyCapacity, double maxEnergyInput, double maxEnergyOutput) {
        super(type, worldPosition, blockState);

        this.energyCapacity = energyCapacity;
        this.maxEnergyInput = maxEnergyInput;
        this.maxEnergyOutput = maxEnergyOutput;
    }

    @Override
    public double getEnergy() {
        return storedEnergy;
    }

    @Override
	public double getEnergyCapacity() {
		return energyCapacity;
	}

	@Override
	public double insert(double amount, Simulation simulation) {
		double inserted = Math.min(Math.min(amount, maxEnergyInput), energyCapacity - storedEnergy);

		if (simulation.isActing() == true) storedEnergy += inserted;

		return amount - inserted;
	}

	@Override
	public double extract(double maxAmount, Simulation simulation) {
		double extracted = Math.min(Math.min(maxAmount, maxEnergyOutput), storedEnergy);

		if (simulation.isActing() == true) storedEnergy -= extracted;

		return extracted;
	}

    @Override
    public boolean supportsExtraction() {return maxEnergyOutput > 0;}

    @Override
    public boolean supportsInsertion() {return maxEnergyInput > 0;}

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        storedEnergy = tag.getDouble("energy");
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        super.save(tag);

        tag.putDouble("energy", storedEnergy);

        return tag;
    }
}
