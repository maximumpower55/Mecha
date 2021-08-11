package me.maximumpower55.mecha;

import me.maximumpower55.mecha.api.registry.MechaBlocks;
import me.maximumpower55.mecha.api.utils.CommonBlockSetting;
import me.maximumpower55.mecha.world.level.block.machine.CompressorMachineBlock;
import me.maximumpower55.mecha.world.level.block.machine.InfuserMachineBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.BlockItem;

public final class ModBlocks {
    public static final InfuserMachineBlock INFUSER_MACHINE_BLOCK = new InfuserMachineBlock(CommonBlockSetting.METAL.get());
    public static final CompressorMachineBlock COMPRESSOR_MACHINE_BLOCK = new CompressorMachineBlock(CommonBlockSetting.METAL.get());

    public static void init() {
        // RegistryUtils.registerBlock(MechaMod.id("basic_cable_block"), BASIC_CABLE_BLOCK, new FabricItemSettings().tab(MechaMod.ITEM_GROUP));

        MechaBlocks.addBlockWithItem(MechaMod.id("infuser_machine_block"), INFUSER_MACHINE_BLOCK, new BlockItem(INFUSER_MACHINE_BLOCK, new FabricItemSettings().tab(MechaMod.ITEM_GROUP)));
        MechaBlocks.addBlockWithItem(MechaMod.id("compressor_machine_block"), COMPRESSOR_MACHINE_BLOCK, new BlockItem(COMPRESSOR_MACHINE_BLOCK, new FabricItemSettings().tab(MechaMod.ITEM_GROUP)));
    }
}
