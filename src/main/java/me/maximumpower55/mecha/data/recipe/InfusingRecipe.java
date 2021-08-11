package me.maximumpower55.mecha.data.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import me.maximumpower55.mecha.ModRecipeSerializers;
import me.maximumpower55.mecha.ModRecipes;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class InfusingRecipe implements Recipe<WorldlyContainer>  {
    private final ResourceLocation id;

    private final double energyUsed;
    private final Ingredient inputA;
    private final Ingredient inputB;
    private final ItemStack result;

	public InfusingRecipe(ResourceLocation id, double energyUsed, ItemStack result, Ingredient inputA, Ingredient inputB) {
		this.id = id;

        this.energyUsed = energyUsed;
		this.inputA = inputA;
		this.inputB = inputB;
		this.result = result;
	}

    @Override
    public ItemStack assemble(WorldlyContainer container) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int arg0, int arg1) {
        return false;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public double getEnergyUsed() {
        return energyUsed;
    }

    public Ingredient getInputA() {
		return inputA;
	}

	public Ingredient getInputB() {
		return inputB;
	}

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.INFUSING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.INFUSING_RECIPE_TYPE;
    }

    @Override
    public boolean matches(WorldlyContainer inv, Level world) {
        if(inv.getContainerSize() > 3) return false;

        return inputA.test(inv.getItem(0)) && inputB.test(inv.getItem(1));
    }

    public static class JsonFormat {
        double energyUsed;
        JsonObject inputA;
        JsonObject inputB;
        String result;
    }

    public static class Serializer implements RecipeSerializer<InfusingRecipe> {
        @Override
        public InfusingRecipe fromJson(ResourceLocation id, JsonObject json) {
            JsonFormat recipeJson = new Gson().fromJson(json, JsonFormat.class);
            if(recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.result == null) throw new JsonSyntaxException("A required attribute is missing!");

            double energyUsed = recipeJson.energyUsed;
            Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
            Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
            Item result = Registry.ITEM.getOptional(new ResourceLocation(recipeJson.result)).orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.result));

            ItemStack resultStack = new ItemStack(result, 1);

            return new InfusingRecipe(id, energyUsed, resultStack, inputA, inputB);
        }

        @Override
        public InfusingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf packet) {
            double energyUsed = packet.readDouble();
            Ingredient inputA = Ingredient.fromNetwork(packet);
            Ingredient inputB = Ingredient.fromNetwork(packet);
            ItemStack result = packet.readItem();

            return new InfusingRecipe(id, energyUsed, result, inputA, inputB);
        }

        @Override
        public void toNetwork(FriendlyByteBuf packet, InfusingRecipe recipe) {
            packet.writeDouble(recipe.getEnergyUsed());
            recipe.getInputA().toNetwork(packet);
            recipe.getInputB().toNetwork(packet);
            packet.writeItem(recipe.result);
        }
    }
}
