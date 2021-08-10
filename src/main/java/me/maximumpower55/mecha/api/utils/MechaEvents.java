package me.maximumpower55.mecha.api.utils;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

public final class MechaEvents {
    public static final Event<ModelReloadEvent> RELOAD_MODELS = EventFactory.createArrayBacked(ModelReloadEvent.class, (listeners) -> (bakery, resourceManager, profiler) -> {
        for(ModelReloadEvent listener : listeners) {
            listener.onReload(bakery, resourceManager, profiler);
        }
    });

    @FunctionalInterface
    public interface ModelReloadEvent {
        void onReload(ModelBakery bakery, ResourceManager resourceManager, ProfilerFiller profiler);
    }
}
