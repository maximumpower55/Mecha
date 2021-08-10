package me.maximumpower55.mecha;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MechaMod implements ModInitializer {
	public static final String MOD_ID = "mecha";

	public static CreativeModeTab ITEM_GROUP;

	@Override
	public void onInitialize() {
		ITEM_GROUP = FabricItemGroupBuilder.create(id("main")).icon(() -> new ItemStack(ModBlocks.INFUSER_MACHINE_BLOCK)).build();

		ModBlocks.register();
		ModBlockEntities.register();

		ModOres.register();

		ModItems.register();

		ModRecipes.register();
		ModRecipeSerializers.register();

		ModScreens.register();
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
