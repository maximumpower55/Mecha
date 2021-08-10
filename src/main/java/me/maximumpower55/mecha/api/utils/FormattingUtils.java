package me.maximumpower55.mecha.api.utils;

public final class FormattingUtils {
    public static String formatEnergy(double energy) {
        if(energy >= 1000000000000d) return String.format("%.1f", energy / 1000000000000d) + "T";
        if(energy >= 1000000000d) return String.format("%.1f", energy / 1000000000d) + "B";
        if(energy >= 1000000d) return String.format("%.1f", energy / 1000000d) + "M";
        if(energy >= 1000d) return String.format("%.1f", energy / 1000d) + "K";

        return String.format("%.1f", energy);
    }
}
