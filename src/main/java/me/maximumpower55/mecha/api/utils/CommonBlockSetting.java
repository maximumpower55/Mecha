package me.maximumpower55.mecha.api.utils;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.world.level.block.Blocks;

public enum CommonBlockSetting {
    LITE_ORE(FabricBlockSettings.copyOf(Blocks.IRON_ORE).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()),
    HEAVY_ORE(FabricBlockSettings.copyOf(Blocks.IRON_ORE).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool()),
    METAL(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());

    private final FabricBlockSettings blockSettings;

    CommonBlockSetting(FabricBlockSettings blockSettings) {
        this.blockSettings = blockSettings;
    }

    public FabricBlockSettings get() {
        return blockSettings;
    }
}
