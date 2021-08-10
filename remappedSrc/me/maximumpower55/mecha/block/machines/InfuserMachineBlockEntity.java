package me.maximumpower55.mecha.block.machines;

import me.maximumpower55.mecha.ModBlockEntities;
import me.maximumpower55.mecha.ModRecipes;
import me.maximumpower55.mecha.core.machine.block.BaseMachineBlockEntity;
import me.maximumpower55.mecha.gui.screens.InfuserMachineBlockGuiDescription;
import me.maximumpower55.mecha.recipe.InfusingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class InfuserMachineBlockEntity extends BaseMachineBlockEntity {
    public InfuserMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INFUSER_MACHINE_BLOCK_ENTITY, pos, state, 100d, 20d, 0, 3, new int[] {0, 1, 2}, new int[] {2});
    }

    @Override
    public void tick(Level world, BlockPos pos, BlockState state, BaseMachineBlockEntity blockEntity) {
        if(world == null) return;

        InfusingRecipe recipe = getRecipe();
        ItemStack output = getOutput();

        if(recipe != null & output != ItemStack.EMPTY) {
            ItemStack currentOutput = inventoryComponent.getStack(2);

            if(!currentOutput.isEmpty()) {
                if(currentOutput.getCount() != currentOutput.getMaxStackSize()) {
                    inventoryComponent.removeStack(0, 1);
                    inventoryComponent.removeStack(1, 1);
                }
            } else {
                inventoryComponent.removeStack(0, 1);
                inventoryComponent.removeStack(1, 1);
            }

            if(!currentOutput.isEmpty()) {
                int newCount = currentOutput.getCount() + output.getCount();

                if(!ItemStack.isSameIgnoreDurability(currentOutput, output) || newCount > output.getMaxStackSize()) {
                    return;
                }
            }

            if(!currentOutput.isEmpty()) {
                storedEnergy -= recipe.getEnergyUsed();

                currentOutput.grow(output.getCount());
            } else {
                storedEnergy -= recipe.getEnergyUsed();

                inventoryComponent.setStack(2, output);
            }
        }
    }

    private InfusingRecipe getRecipe() {
        return world.getRecipeManager().getFirstMatch(ModRecipes.INFUSING_RECIPE_TYPE, inventoryComponent, world).orElse(null);
    }

    private ItemStack getOutput() {
        InfusingRecipe recipe = getRecipe();

        if(recipe != null) if(storedEnergy >= recipe.getEnergyUsed()) return recipe.craft(inventoryComponent);

        return ItemStack.EMPTY;
    }

    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return new InfuserMachineBlockGuiDescription(syncId, playerInventory, ContainerLevelAccess.create(world, pos));
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("container.mecha.infuser");
    }
}
