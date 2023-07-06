package com.dot.nbm.doers;

import android.telephony.CellSignalStrength;

import com.dot.nbm.model.SignalState;

public class SignalStrengthLevelIndicator {
    public enum SignalLevel {
        GREAT,
        GOOD,
        MODERATE,
        POOR,

        UNKNOWN
    }

    public static SignalLevel getSignalStrengthLevel(SignalState signalState) {

        if (signalState.getLevel() == CellSignalStrength.SIGNAL_STRENGTH_GREAT)
            return SignalLevel.GREAT;
        else if (signalState.getLevel() == CellSignalStrength.SIGNAL_STRENGTH_GOOD)
            return SignalLevel.GOOD;
        else if (signalState.getLevel() == CellSignalStrength.SIGNAL_STRENGTH_MODERATE)
            return SignalLevel.MODERATE;
        else if (signalState.getLevel() == CellSignalStrength.SIGNAL_STRENGTH_POOR)
            return SignalLevel.POOR;
        else
            return SignalLevel.UNKNOWN;
    }
}
