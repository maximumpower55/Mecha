package me.maximumpower55.mecha.api.utils;

import net.minecraft.resources.ResourceLocation;

public final class ResourceUtils {
    public static ResourceLocation prefixPath(ResourceLocation location, String prefix) {
        return new ResourceLocation(location.getNamespace(), prefix + "/" + location.getPath());
    }
}
