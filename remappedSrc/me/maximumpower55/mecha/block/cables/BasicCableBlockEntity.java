package me.maximumpower55.mecha.block.cables;

import dev.technici4n.fasttransferlib.api.energy.EnergyIo;
import dev.technici4n.fasttransferlib.api.energy.EnergyMovement;
import me.maximumpower55.mecha.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BasicCableBlockEntity extends CableBlockEntity {
    private final BlockPos[] SIDES = {pos.north(), pos.south(), pos.up(), pos.down(), pos.east(), pos.west()};

    public BasicCableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASIC_CABLE_BLOCK_ENTITY, pos, state, 20d, 5d, 5d);
    }

    @Override
    public void tick(Level world, BlockPos pos, BlockState state, CableBlockEntity blockEntity) {
        if(world == null) return;

        for (BlockPos sidePos : SIDES) {
            BlockEntity blockEntityAtSide = world.getBlockEntity(sidePos);

            if(blockEntityAtSide instanceof EnergyIo energyIo) {
                storedEnergy -= EnergyMovement.move(this, energyIo, 5d);
            }
        }
    }
}
