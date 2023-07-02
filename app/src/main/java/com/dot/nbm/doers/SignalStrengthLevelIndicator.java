package com.dot.nbm.doers;

import android.content.Context;
import android.content.res.Resources;

import com.dot.nbm.R;

public class SignalStrengthLevelIndicator {
    public enum SignalLevel {
        EXCELLENT,
        GOOD,
        FAIR,
        POOR
    }

    public static SignalLevel getSignalStrengthLevel(Context context, String generation, int signalStrength){

        Resources resources = context.getResources();
        // For 2G
        if (generation.equals(context.getString(R.string.TWO_G))){
            int[] two_g_signal_levels = resources.getIntArray(R.array.TWO_G_LEVELS);
            return getLevel(signalStrength, two_g_signal_levels);
        }
        // For 3G
        else if (generation.equals(context.getString(R.string.THREE_G))){
            int[] three_g_signal_levels = resources.getIntArray(R.array.THREE_G_LEVELS);
            return getLevel(signalStrength, three_g_signal_levels);
        }
        // For 4G
        else if (generation.equals(context.getString(R.string.FOUR_G))){
            int[] four_g_signal_levels = resources.getIntArray(R.array.FOUR_G_LEVELS);
            return getLevel(signalStrength, four_g_signal_levels);
        }
        // For 5G
        else if (generation.equals(context.getString(R.string.FIVE_G))){
            int[] five_g_signal_levels = resources.getIntArray(R.array.FIVE_G_LEVELS);
            return getLevel(signalStrength, five_g_signal_levels);
        }
        return null;
    }

    private static SignalLevel getLevel(int signalStrength, int[]signal_levels){
        if(signalStrength >= signal_levels[0])
            return SignalLevel.EXCELLENT;
        else if (signalStrength >=signal_levels[1]) {
            return SignalLevel.GOOD;
        }
        else if (signalStrength >=signal_levels[2]) {
            return SignalLevel.FAIR;
        }
        else
            return SignalLevel.POOR;
    }
}
