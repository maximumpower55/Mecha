package me.maximumpower55.mecha.api.component;

import java.util.stream.IntStream;

import com.google.common.base.Preconditions;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;

public final class ContainerComponent extends SimpleContainer implements Component, WorldlyContainer {
    private final int[] inputSlots;
    private final int[] outputSlots;

    public ContainerComponent(int size, int[] inputSlots, int[] outputSlots) {
        super(size);

        this.inputSlots = inputSlots;
        this.outputSlots = outputSlots;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return null;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return IntStream.of(inputSlots).anyMatch(n -> n == slot);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return IntStream.of(outputSlots).anyMatch(n -> n == slot);
    }

    @Override
    public void loadFromNbt(CompoundTag tag) {
        Preconditions.checkNotNull(tag);

        fromTag(tag.getList("Container", 0));
    }

    @Override
    public void saveToNbt(CompoundTag tag) {
        Preconditions.checkNotNull(tag);

        tag.put("Container", createTag());
    }
}
