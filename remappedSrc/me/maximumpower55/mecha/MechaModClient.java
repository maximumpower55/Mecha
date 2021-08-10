package me.maximumpower55.mecha;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.util.Pair;

import me.maximumpower55.mecha.core.utils.ResourceUtils;
import me.maximumpower55.mecha.models.block.CableBlockModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class MechaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModels.load();
        ModBlockEntityRenderers.register();
        ModScreens.registerClient();

        ModelLoadingRegistry.INSTANCE.registerResourceProvider(resourceManager -> (resourceId, context) -> {
            ResourceLocation basic_cable_model_block_id = ResourceUtils.prefixPath(MechaMod.id("basic_cable_block"), "block");
            ResourceLocation basic_cable_model_item_id = ResourceUtils.prefixPath(MechaMod.id("basic_cable_block"), "item");

            if(resourceId.equals(basic_cable_model_block_id) || resourceId.equals(basic_cable_model_item_id)) {
                return new UnbakedModel() {
                    @Override
                    public Collection<ResourceLocation> getDependencies() {
                        return Collections.emptyList();
                    }

                    @Override
                    public Collection<Material> getMaterials(Function<ResourceLocation, UnbakedModel> unbakedModelGetter, Set<Pair<String, String>> unresolvedTextureReferences) {
                        return Collections.emptyList();
                    }

                    @Override
                    public BakedModel bake(ModelBakery loader, Function<Material, TextureAtlasSprite> textureGetter, ModelState rotationContainer, ResourceLocation modelId) {
                        return new CableBlockModel(ModModels.BASIC_CABLE_BLOCK_CENTER, ModModels.BASIC_CABLE_BLOCK_CONNECTIONS);
                    }
                };
            }

            return null;
        });
    }
}
