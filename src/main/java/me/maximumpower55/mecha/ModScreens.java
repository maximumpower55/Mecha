package me.maximumpower55.mecha;

import me.maximumpower55.mecha.client.gui.screens.InfuserMachineBlockGuiDescription;
import me.maximumpower55.mecha.client.gui.screens.InfuserMachineBlockScreen;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;

public class ModScreens {
    public static MenuType<InfuserMachineBlockGuiDescription> INFUSER_SCREEN_HANDLER_TYPE = null;

    public static void register() {
        INFUSER_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(MechaMod.id("infuser_machine_block"), (syncId, inventory) -> new InfuserMachineBlockGuiDescription(syncId, inventory, ContainerLevelAccess.NULL));
    }

    public static void registerClient() {
        ScreenRegistry.<InfuserMachineBlockGuiDescription, InfuserMachineBlockScreen>register(INFUSER_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new InfuserMachineBlockScreen(gui, inventory.player, title));
    }
}
