package me.maximumpower55.mecha;

import me.maximumpower55.mecha.block.cables.BasicCableBlock;
import me.maximumpower55.mecha.block.machines.CompressorMachineBlock;
import me.maximumpower55.mecha.block.machines.InfuserMachineBlock;
import me.maximumpower55.mecha.core.utils.CommonBlockSetting;
import me.maximumpower55.mecha.core.utils.RegistryUtils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class ModBlocks {
    public static final BasicCableBlock BASIC_CABLE_BLOCK = new BasicCableBlock(CommonBlockSetting.METAL.get());
    public static final InfuserMachineBlock INFUSER_MACHINE_BLOCK = new InfuserMachineBlock(CommonBlockSetting.METAL.get());
    public static final CompressorMachineBlock COMPRESSOR_MACHINE_BLOCK = new CompressorMachineBlock(CommonBlockSetting.METAL.get());

    public static void register() {
        RegistryUtils.registerBlock(MechaMod.id("basic_cable_block"), BASIC_CABLE_BLOCK, new FabricItemSettings().tab(MechaMod.ITEM_GROUP));
        RegistryUtils.registerBlock(MechaMod.id("infuser_machine_block"), INFUSER_MACHINE_BLOCK, new FabricItemSettings().tab(MechaMod.ITEM_GROUP));
        RegistryUtils.registerBlock(MechaMod.id("compressor_machine_block"), COMPRESSOR_MACHINE_BLOCK, new FabricItemSettings().tab(MechaMod.ITEM_GROUP));
    }
}
