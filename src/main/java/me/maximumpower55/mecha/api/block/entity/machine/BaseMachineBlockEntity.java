package me.maximumpower55.mecha.api.block.entity.machine;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import me.maximumpower55.mecha.api.block.entity.PowerAcceptorBlockEntity;
import me.maximumpower55.mecha.api.component.Component;
import me.maximumpower55.mecha.api.component.ComponentKey;
import me.maximumpower55.mecha.api.component.ComponentProvider;
import me.maximumpower55.mecha.api.component.ContainerComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BaseMachineBlockEntity extends PowerAcceptorBlockEntity implements ComponentProvider, WorldlyContainerHolder, BlockEntityTicker<BaseMachineBlockEntity>, MenuProvider, PropertyDelegateHolder {
    protected final ContainerComponent containerComponent;

	protected BaseMachineBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState, double energyCapacity, double maxEnergyInput, double maxEnergyOutput, int size, int[] inputSlots, int[] outputSlots) {
		super(type, worldPosition, blockState, energyCapacity, maxEnergyInput, maxEnergyOutput);

        this.containerComponent = new ContainerComponent(size, inputSlots, outputSlots);
	}

    @Override
    public Component getComponent(ComponentKey componentKey) {
        switch(componentKey) {
            case CONTAINER:
                return containerComponent;
            default:
                return null;
        }
    }

    @Override
    public WorldlyContainer getContainer(BlockState state, LevelAccessor level, BlockPos pos) {
        return containerComponent;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        containerComponent.loadFromNbt(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        super.save(tag);

        containerComponent.saveToNbt(tag);

        return tag;
    }

    @Override
    public ContainerData getPropertyDelegate() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0: return (int)storedEnergy;
                    case 1: return (int)energyCapacity;
                    default: throw new IllegalArgumentException(String.format("Unknown property key: %s", index));
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: storedEnergy = (double)value;
                    default: throw new IllegalArgumentException(String.format("Unknown property key: %s", index));
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
}
