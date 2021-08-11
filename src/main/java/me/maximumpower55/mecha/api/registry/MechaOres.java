package me.maximumpower55.mecha.api.registry;

import java.util.ArrayList;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class MechaOres {
    private static final ArrayList<OreEntry> ORES = new ArrayList<>();

    public static Block addOre(ResourceLocation location, FabricBlockSettings oreBlockSettings, FabricBlockSettings blockSettings, FabricItemSettings itemSettings) {
        final Block oreBlock = new Block(oreBlockSettings);
        final Item rawItem = new Item(itemSettings);
        final Item ingotItem = new Item(itemSettings);
        final Block block = new Block(blockSettings);

        OreEntry entry = new OreEntry(new ResourceLocation(location.getNamespace(), location.getPath() + "_ore"), new ResourceLocation(location.getNamespace(), "raw_" + location.getPath()), new ResourceLocation(location.getNamespace(), location.getPath() + "_ingot"), new ResourceLocation(location.getNamespace(), location.getPath() + "_block"), oreBlock, rawItem, ingotItem, block);

        ORES.add(entry);

        return oreBlock;
    }

    public static void register(String namespace) {
        for (OreEntry entry : ORES) {
            if(namespace != entry.oreLocation.getNamespace()) continue;

            Registry.register(Registry.BLOCK, entry.oreLocation, entry.oreBlock);
            Registry.register(Registry.ITEM, entry.oreLocation, new BlockItem(entry.oreBlock, new FabricItemSettings().tab(entry.rawItem.getItemCategory())));
            Registry.register(Registry.ITEM, entry.rawLocation, entry.rawItem);
            Registry.register(Registry.ITEM, entry.ingotLocation, entry.ingotItem);
            Registry.register(Registry.BLOCK, entry.blockLocation, entry.block);
            Registry.register(Registry.ITEM, entry.blockLocation, new BlockItem(entry.block, new FabricItemSettings().tab(entry.rawItem.getItemCategory())));
        }
    }

    private record OreEntry(ResourceLocation oreLocation, ResourceLocation rawLocation, ResourceLocation ingotLocation, ResourceLocation blockLocation, Block oreBlock, Item rawItem, Item ingotItem, Block block) {}
}
