package me.maximumpower55.mecha.api;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Predicate;

import org.jetbrains.annotations.ApiStatus.Internal;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

@SuppressWarnings("deprecation")
public final class MechaBiomeModifications {
    private static final WeakHashMap<WorldType, OreModification> ORE_MODIFICATIONS = new WeakHashMap<>();

    public static final WorldType OVERWORLD = (WorldType)BiomeSelectors.foundInOverworld();
    public static final WorldType NETHER = (WorldType)BiomeSelectors.foundInTheNether();
    public static final WorldType END = (WorldType)BiomeSelectors.foundInTheEnd();

	public static void addOre(WorldType worldType, GenerationStep.Decoration step, ResourceLocation location, ConfiguredFeature<?, ?> feature) {
        ResourceKey<ConfiguredFeature<?, ?>> featureKey = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, location);

        ORE_MODIFICATIONS.put(worldType, new OreModification(step, featureKey, feature));
	}

    public static void register(String namespace) {
        for (Map.Entry<WorldType, OreModification> entry : ORE_MODIFICATIONS.entrySet()) {
            if(namespace != entry.getValue().featureKey.location().getNamespace()) continue;

            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, entry.getValue().featureKey.location(), entry.getValue().feature);

            BiomeModifications.addFeature(entry.getKey(), entry.getValue().step, entry.getValue().featureKey);
        }
    }

    private record OreModification(GenerationStep.Decoration step, ResourceKey<ConfiguredFeature<?, ?>> featureKey, ConfiguredFeature<?, ?> feature) {}

    @Internal
    @FunctionalInterface
    public interface WorldType extends Predicate<BiomeSelectionContext> {}
}
