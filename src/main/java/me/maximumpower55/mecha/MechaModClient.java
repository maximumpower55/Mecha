package me.maximumpower55.mecha;

import net.fabricmc.api.ClientModInitializer;

public class MechaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModels.load();
        ModBlockEntityRenderers.register();
        ModScreens.registerClient();
    }
}
