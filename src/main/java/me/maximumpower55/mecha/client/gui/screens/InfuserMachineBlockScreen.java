package me.maximumpower55.mecha.client.gui.screens;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class InfuserMachineBlockScreen extends CottonInventoryScreen<InfuserMachineBlockGuiDescription> {
    public InfuserMachineBlockScreen(InfuserMachineBlockGuiDescription description, Player player, Component title) {
        super(description, player, title);
    }
}
