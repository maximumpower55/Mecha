package me.maximumpower55.mecha.block.machines;

import me.maximumpower55.mecha.ModBlockEntities;
import me.maximumpower55.mecha.core.machine.block.BaseMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CompressorMachineBlockEntity extends BaseMachineBlockEntity {
    private float compressorProgress = 0f;

    public CompressorMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COMPRESSOR_MACHINE_BLOCK_ENTITY, pos, state, 100d, 20d, 0d, 3, new int[] {0, 1, 2}, new int[] {2});
    }

    @Override
    public void tick(Level world, BlockPos pos, BlockState state, BaseMachineBlockEntity blockEntity) {}

    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return null;
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("container.mecha.compressor");
    }

    public float getCompressorProgress() {
        return compressorProgress;
    }
}
