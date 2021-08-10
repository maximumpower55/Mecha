package me.maximumpower55.mecha;

import me.maximumpower55.mecha.recipe.InfusingRecipe;
import net.minecraft.core.Registry;

public class ModRecipeSerializers {
    public static final InfusingRecipe.Serializer INFUSING_RECIPE_SERIALIZER = new InfusingRecipe.Serializer();

    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, MechaMod.id("infuser"), INFUSING_RECIPE_SERIALIZER);
    } 
}
