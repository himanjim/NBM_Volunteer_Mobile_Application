package com.dot.nbm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.dot.nbm.model.MainActivityViewModel;

import java.util.List;

public class SignalStateFetcher {

    public MainActivityViewModel getSignalStrength(Context context) throws SecurityException {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String strength = null;
        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();   //This will give info of all sims present inside your mobile
        MainActivityViewModel signalViewModel = null;
        if (cellInfos != null) {
            signalViewModel = new MainActivityViewModel();
            Log.d("cellInfos", cellInfos.toString());
            for (int i = 0; i < cellInfos.size(); i++) {
                if (cellInfos.get(i).isRegistered()) {
                    if (cellInfos.get(i) instanceof CellInfoWcdma) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfos.get(i);
                        CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
                        strength = String.valueOf(cellSignalStrengthWcdma.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoGsm) {
                        CellInfoGsm cellInfogsm = (CellInfoGsm) cellInfos.get(i);
                        CellSignalStrengthGsm cellSignalStrengthGsm = cellInfogsm.getCellSignalStrength();
                        strength = String.valueOf(cellSignalStrengthGsm.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfos.get(i);
                        CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
                        signalViewModel.setOperator(getOperatorName(context));
                        signalViewModel.setSignalStrength(cellSignalStrengthLte.getDbm());
                        signalViewModel.setTechnology("4G");
                        strength = String.valueOf(cellSignalStrengthLte.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoCdma) {
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfos.get(i);
                        CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
                        strength = String.valueOf(cellSignalStrengthCdma.getDbm());
                    }
                }
            }
        }
        return signalViewModel;
    }

    private String getOperatorName(Context context) {
        String operatorsName = null;
        if (Build.VERSION.SDK_INT > 22) {
            //for dual sim mobile
            SubscriptionManager localSubscriptionManager = SubscriptionManager.from(context);
            if (ContextCompat.checkSelfPermission(
                    context, android.Manifest.permission.READ_PHONE_STATE) ==
                    PackageManager.PERMISSION_GRANTED) {
                if (localSubscriptionManager.getActiveSubscriptionInfoCount() > 1) {
                    //if there are two sims in dual sim mobile
                    List localList = localSubscriptionManager.getActiveSubscriptionInfoList();
                    SubscriptionInfo simInfo = (SubscriptionInfo) localList.get(0);
                    SubscriptionInfo simInfo1 = (SubscriptionInfo) localList.get(1);

                    final String sim1 = simInfo.getDisplayName().toString();
                    final String sim2 = simInfo1.getDisplayName().toString();
                    operatorsName = String.join(" and ", sim1, sim2);

                } else {
                    //if there is 1 sim in dual sim mobile
                    TelephonyManager tManager = (TelephonyManager) context
                            .getSystemService(Context.TELEPHONY_SERVICE);

                    operatorsName = tManager.getNetworkOperatorName();

                }

            }
        } else {
            //below android version 22
            TelephonyManager tManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            operatorsName = tManager.getNetworkOperatorName();
        }


        return operatorsName;
    }
}
