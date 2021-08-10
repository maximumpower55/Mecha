package me.maximumpower55.mecha.gui.screens;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import me.maximumpower55.mecha.ModScreens;
import me.maximumpower55.mecha.core.gui.widgets.WEnergyBar;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class InfuserMachineBlockGuiDescription extends SyncedGuiDescription {
    public InfuserMachineBlockGuiDescription(int syncId, Inventory playerInventory, ContainerLevelAccess ctx) {
        super(ModScreens.INFUSER_SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(ctx, INV_SIZE), getBlockPropertyDelegate(ctx, PD_SIZE));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(100, 100);
        root.setInsets(Insets.ROOT_PANEL);

        WEnergyBar energyBar = new WEnergyBar();
        root.add(energyBar, 0, 0, 1, 3);

        WItemSlot itemSlotA = WItemSlot.of(blockInventory, 0);
        root.add(itemSlotA, 2, 1);

        WItemSlot itemSlotB = WItemSlot.of(blockInventory, 1);
        root.add(itemSlotB, 6, 1);

        WItemSlot output = WItemSlot.of(blockInventory, 2);
        output.setInsertingAllowed(false);
        root.add(output, 4, 3);

        root.add(createPlayerInventoryPanel(), 0, 4);
        root.validate(this);
    }

    private static final int PD_SIZE = 2;
    private static final int INV_SIZE = 3;
}
