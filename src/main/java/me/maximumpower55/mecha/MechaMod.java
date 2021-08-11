package me.maximumpower55.mecha;

import me.maximumpower55.mecha.api.registry.MechaBiomeModifications;
import me.maximumpower55.mecha.api.registry.MechaBlockEntities;
import me.maximumpower55.mecha.api.registry.MechaBlocks;
import me.maximumpower55.mecha.api.registry.MechaItems;
import me.maximumpower55.mecha.api.registry.MechaOres;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MechaMod implements ModInitializer {
    public static final String MOD_ID = "mecha";

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

    public static CreativeModeTab ITEM_GROUP;

	@Override
	public void onInitialize() {
        ITEM_GROUP = FabricItemGroupBuilder.create(id("main")).icon(() -> new ItemStack(ModBlocks.INFUSER_MACHINE_BLOCK)).build();

		ModBlocks.init();
		ModBlockEntities.init();
		ModOres.init();
		ModItems.init();
		ModRecipes.register();
		ModRecipeSerializers.register();
		ModScreens.register();

        MechaBlocks.register("mecha");
        MechaBlockEntities.register("mecha");
        MechaOres.register("mecha");
        MechaItems.register("mecha");
        MechaBiomeModifications.register("mecha");
    }
}
