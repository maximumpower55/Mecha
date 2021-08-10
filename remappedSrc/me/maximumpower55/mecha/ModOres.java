package me.maximumpower55.mecha;

import me.maximumpower55.mecha.core.utils.CommonBlockSetting;
import me.maximumpower55.mecha.core.utils.RegistryUtils;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

public class ModOres {
    @SuppressWarnings("deprecation")
    public static void register() {
        final Block TIN_ORE = RegistryUtils.registerOre(MechaMod.id("tin"), CommonBlockSetting.LITE_ORE.get(), CommonBlockSetting.METAL.get(), new FabricItemSettings().tab(MechaMod.ITEM_GROUP));
        final ConfiguredFeature<?, ?> TIN_ORE_OVERWORLD = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, TIN_ORE.defaultBlockState(), 9)).decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))))).squared().count(20);
        ResourceKey<ConfiguredFeature<?, ?>> tinOreKey = ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, MechaMod.id("tin_ore_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, tinOreKey.location(), TIN_ORE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, tinOreKey);
    }
}
