package me.maximumpower55.mecha;

import me.maximumpower55.mecha.recipe.InfusingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final RecipeType<InfusingRecipe> INFUSING_RECIPE_TYPE = new RecipeType<InfusingRecipe>() {
        @Override
        public String toString() {return "infuser";}
    };

    public static void register() {
        Registry.register(Registry.RECIPE_TYPE, MechaMod.id("infuser"), INFUSING_RECIPE_TYPE);
    }
}
