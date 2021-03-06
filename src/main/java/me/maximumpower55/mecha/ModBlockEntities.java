package me.maximumpower55.mecha;

import me.maximumpower55.mecha.api.registry.MechaBlockEntities;
import me.maximumpower55.mecha.world.level.block.entity.machine.CompressorMachineBlockEntity;
import me.maximumpower55.mecha.world.level.block.entity.machine.InfuserMachineBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    // public static final BlockEntityType<BasicCableBlockEntity> BASIC_CABLE_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(BasicCableBlockEntity::new, ModBlocks.BASIC_CABLE_BLOCK).build(null);
    public static final BlockEntityType<InfuserMachineBlockEntity> INFUSER_MACHINE_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(InfuserMachineBlockEntity::new, ModBlocks.INFUSER_MACHINE_BLOCK).build(null);
    public static final BlockEntityType<CompressorMachineBlockEntity> COMPRESSOR_MACHINE_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(CompressorMachineBlockEntity::new, ModBlocks.COMPRESSOR_MACHINE_BLOCK).build(null);

    public static void init() {
        // RegistryUtils.registerBlockEntity(MechaMod.id("basic_cable_block_entity"), BASIC_CABLE_BLOCK_ENTITY);

        MechaBlockEntities.addBlockEntity(MechaMod.id("infuser_machine_block_entity"), INFUSER_MACHINE_BLOCK_ENTITY);
        MechaBlockEntities.addBlockEntity(MechaMod.id("compressor_machine_block_entity"), COMPRESSOR_MACHINE_BLOCK_ENTITY);
    }
}
