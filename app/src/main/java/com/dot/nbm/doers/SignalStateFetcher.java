package com.dot.nbm.doers;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthNr;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.dot.nbm.R;
import com.dot.nbm.model.SignalState;

import java.util.ArrayList;
import java.util.List;

public class SignalStateFetcher {

    public static List<SignalState> getSignalState(Context context) throws SecurityException {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();   //This will give info of all sims present inside your mobile

        if (cellInfos != null) {
            int strength = 0;
            String operatorName = null;
            int rfChannelNo = 0;
            String generation = null;
            String technology = null;

            List<SignalState> signalStates = new ArrayList<>();

            Log.d("cellInfos", cellInfos.toString());
            for (CellInfo cellInfo : cellInfos) {

                if (cellInfo.isRegistered()) {

                    if (cellInfo instanceof CellInfoWcdma) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                        CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();

                        strength = cellSignalStrengthWcdma.getDbm();
                        rfChannelNo = cellInfoWcdma.getCellIdentity().getUarfcn();
                        operatorName = cellInfoWcdma.getCellIdentity().getOperatorAlphaLong().toString();
                        generation = context.getString(R.string.THREE_G);
                        technology = context.getString(R.string.WCDMA);

                    } else if (cellInfo instanceof CellInfoGsm) {
                        CellInfoGsm cellInfogsm = (CellInfoGsm) cellInfo;
                        CellSignalStrengthGsm cellSignalStrengthGsm = cellInfogsm.getCellSignalStrength();

                        strength = cellSignalStrengthGsm.getDbm();
                        rfChannelNo = cellInfogsm.getCellIdentity().getArfcn();
                        operatorName = cellInfogsm.getCellIdentity().getOperatorAlphaLong().toString();
                        generation = context.getString(R.string.TWO_G);
                        technology = context.getString(R.string.GSM);

                    } else if (cellInfo instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                        CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();

                        strength = cellSignalStrengthLte.getDbm();
                        rfChannelNo = cellInfoLte.getCellIdentity().getEarfcn();
                        operatorName = cellInfoLte.getCellIdentity().getOperatorAlphaLong().toString();
                        generation = context.getString(R.string.FOUR_G);
                        technology = context.getString(R.string.LTE);

                    } else if (cellInfo instanceof CellInfoCdma) {
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                        CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();

                        strength = cellSignalStrengthCdma.getDbm();
                        rfChannelNo = cellInfoCdma.getCellIdentity().getNetworkId();
                        operatorName = cellInfoCdma.getCellIdentity().getOperatorAlphaLong().toString();
                        generation = context.getString(R.string.TWO_G);
                        technology = context.getString(R.string.CDMA);

                    } else if (cellInfo instanceof CellInfoNr) {
                        CellInfoNr cellInfoNr = (CellInfoNr) cellInfo;
                        CellSignalStrengthNr cellSignalStrengthNr = (CellSignalStrengthNr) cellInfoNr.getCellSignalStrength();

                        strength = cellSignalStrengthNr.getDbm();
                        operatorName = cellInfoNr.getCellIdentity().getOperatorAlphaLong().toString();
                        generation = context.getString(R.string.FIVE_G);
                        technology = context.getString(R.string.NR);
                    }
                }

                SignalState signalState = new SignalState();
                signalState.setOperaterName(operatorName);
                signalState.setSignalStrength(strength);
                signalState.setChannelNo(rfChannelNo);
                signalState.setTechnology(technology);
                signalState.setGeneration(generation);

                signalStates.add(signalState);
            }


            return signalStates;

        }

        return null;
    }

}
