package me.maximumpower55.mecha.api.component;

import org.jetbrains.annotations.Contract;

import net.minecraft.nbt.CompoundTag;

public interface Component {
    @Contract(mutates = "this")
    void loadFromNbt(CompoundTag tag);

    @Contract(mutates = "param")
    void saveToNbt(CompoundTag tag);
}
