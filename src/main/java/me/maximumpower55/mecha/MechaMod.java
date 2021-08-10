package me.maximumpower55.mecha;

import me.maximumpower55.mecha.api.registry.MechaBiomeModifications;
import me.maximumpower55.mecha.api.registry.MechaBlocks;
import me.maximumpower55.mecha.api.registry.MechaItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class MechaMod implements ModInitializer {
    public static final String MOD_ID = "mecha";

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
        ModBlocks.init();
        ModItems.init();

        MechaBlocks.register("mecha");
        MechaItems.register("mecha");
        MechaBiomeModifications.register("mecha");
    }
}
