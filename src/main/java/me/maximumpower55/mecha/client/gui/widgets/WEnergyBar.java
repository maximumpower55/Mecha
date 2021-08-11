package me.maximumpower55.mecha.client.gui.widgets;

import io.github.cottonmc.cotton.gui.widget.TooltipBuilder;
import io.github.cottonmc.cotton.gui.widget.WBar;
import me.maximumpower55.mecha.MechaMod;
import me.maximumpower55.mecha.api.utils.FormattingUtils;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

public class WEnergyBar extends WBar {
    private static final ResourceLocation ENERGY_BAR_EMPTY = MechaMod.id("textures/gui/energy_bar_empty.png");
    private static final ResourceLocation ENERGY_BAR_FULL = MechaMod.id("textures/gui/energy_bar_full.png");

    public WEnergyBar() {
        super(ENERGY_BAR_EMPTY, ENERGY_BAR_FULL, 0, 1, WBar.Direction.UP);
    }

    @Override
    public void addTooltip(TooltipBuilder information) {
        String energy = FormattingUtils.formatEnergy(properties.get(0));
        String maxEnergy = FormattingUtils.formatEnergy(properties.get(1));

        information.add(new TextComponent(String.format("%s / %s E", energy, maxEnergy)));
    }
}
