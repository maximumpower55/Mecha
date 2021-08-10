package me.maximumpower55.mecha;

import me.maximumpower55.mecha.api.MechaBiomeModifications;
import me.maximumpower55.mecha.api.MechaBlocks;
import me.maximumpower55.mecha.api.MechaItems;
import net.fabricmc.api.ModInitializer;

public class MechaMod implements ModInitializer {
    public static final String MOD_ID = "mecha";

	@Override
	public void onInitialize() {
        ModBlocks.init();
        ModItems.init();

        MechaBlocks.register("mecha");
        MechaItems.register("mecha");
        MechaBiomeModifications.register("mecha");
    }
}
