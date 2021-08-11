package me.maximumpower55.mecha;

import me.maximumpower55.mecha.api.registry.MechaBiomeModifications;
import me.maximumpower55.mecha.api.registry.MechaOres;
import me.maximumpower55.mecha.api.utils.CommonBlockSetting;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
    public static void init() {
        final Block TIN_ORE = MechaOres.addOre(MechaMod.id("tin"), CommonBlockSetting.LITE_ORE.get(), CommonBlockSetting.METAL.get(), new FabricItemSettings().group(MechaMod.ITEM_GROUP));
        final ConfiguredFeature<?, ?> TIN_ORE_OVERWORLD = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, TIN_ORE.defaultBlockState(), 9)).decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))))).squared().count(20);
        MechaBiomeModifications.addOre(MechaBiomeModifications.OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES, MechaMod.id("tin_ore_overworld"), TIN_ORE_OVERWORLD);
    }
}
