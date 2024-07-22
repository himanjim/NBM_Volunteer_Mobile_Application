package com.dot.nbm.doers;

import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityNr;
import android.telephony.CellIdentityTdscdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoTdscdma;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthNr;
import android.telephony.CellSignalStrengthTdscdma;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;


import com.dot.nbm.R;
import com.dot.nbm.model.SignalState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SignalStateFetcher {

    public static List<SignalState> getSignalState(Context context) throws SecurityException {
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();   //This will give info of all sims present inside your mobile

//        boolean nrConnect = isNRConnected(telephonyManager);

        SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);


        List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
//        StringBuilder networkInfo = new StringBuilder();

        List<SignalState> signalStates = new ArrayList<>();

        int simIndex = 0;

        for (SubscriptionInfo subscriptionInfo : subscriptionInfoList) {

            int subscriptionId = subscriptionInfo.getSubscriptionId();
            TelephonyManager subTelephonyManager = telephonyManager.createForSubscriptionId(subscriptionId);

//            boolean nrConnect = false;

            int networkType = subTelephonyManager.getDataNetworkType();
            String networkTypeString = getNetworkTypeString(networkType, context);

            ServiceState serviceState = subTelephonyManager.getServiceState();
            if (serviceState != null) {
                if (serviceState.toString().contains("isNrAvailable = true"))
                    networkTypeString = context.getString(R.string.FIVE_G);
//                List<NetworkRegistrationInfo> networkRegistrationInfos = null;
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    networkRegistrationInfos = serviceState.getNetworkRegistrationInfoList();
//                }
//                for (NetworkRegistrationInfo networkRegistrationInfo : networkRegistrationInfos) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                        if (networkRegistrationInfo.getAccessNetworkTechnology() == TelephonyManager.NETWORK_TYPE_NR) {
//                            nrConnect = true;
//                        }
//                    }
//                }
            }
//            boolean nrConnect = is5GConnected(context);

//            networkInfo.append("SIM Slot ").append(subscriptionInfo.getSimSlotIndex()).append(" - Network Type: ").append(networkTypeString).append("\n");

            if (networkType == 0)
                continue;

            simIndex++;

            List<CellInfo> cellInfos = subTelephonyManager.getAllCellInfo();


            if (cellInfos != null) {
                int strength = 0;
//                String operatorName = null;
                int rfChannelNo = 0;
//                String generation = null;
                String technology = null;
                long cellId = 0;
                int locationAreaCode = 0;
                int baseStationIdentityCode = 0;
                int trackingAreaCode = 0;
                int physicalCellId = 0;
                int systemId = 0;
                int networkId = 0;
                int level = 0;
                int cpid = 0;
                String mnc = null;
                String mcc = null;

                int rssi = 0;
                int rsrq = 0;
                int csi_rsrp = 0;
                int csi_rsrq = 0;
                int ss_rsrp = 0;
                int ss_rsrq = 0;
                int rssnr = 0;

//            Log.d("cellInfos", cellInfos.toString());
                for (CellInfo cellInfo : cellInfos) {

                    if (cellInfo.isRegistered()) {

                        if (cellInfo instanceof CellInfoWcdma) {
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                            CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();

                            level = cellSignalStrengthWcdma.getLevel();
                            strength = cellSignalStrengthWcdma.getDbm();
                            rfChannelNo = cellInfoWcdma.getCellIdentity().getUarfcn();
                            cellId = cellInfoWcdma.getCellIdentity().getCid();
                            locationAreaCode = cellInfoWcdma.getCellIdentity().getLac();
                            mnc = cellInfoWcdma.getCellIdentity().getMncString();
                            mcc = cellInfoWcdma.getCellIdentity().getMccString();

//                            operatorName = cellInfoWcdma.getCellIdentity().getOperatorAlphaLong().toString();
//                            generation = context.getString(R.string.THREE_G);
                            technology = context.getString(R.string.WCDMA);

                        }
                        if (cellInfo instanceof CellInfoTdscdma) {
                            CellInfoTdscdma cellInfoTdscdma = (CellInfoTdscdma) cellInfo;
                            CellSignalStrengthTdscdma cellSignalStrengthTdscdma = cellInfoTdscdma.getCellSignalStrength();

                            level = cellSignalStrengthTdscdma.getLevel();
                            strength = cellSignalStrengthTdscdma.getDbm();

                            CellIdentityTdscdma cellIdentityTdscdma = cellInfoTdscdma.getCellIdentity();
                            rfChannelNo = cellIdentityTdscdma.getUarfcn();
                            cellId = cellIdentityTdscdma.getCid();
                            locationAreaCode = cellIdentityTdscdma.getLac();
                            cpid = cellIdentityTdscdma.getCpid();
                            mnc = cellIdentityTdscdma.getMncString();
                            mcc = cellIdentityTdscdma.getMccString();

//                            operatorName = cellIdentityTdscdma.getOperatorAlphaLong().toString();
//                            generation = context.getString(R.string.THREE_G);
                            technology = context.getString(R.string.TDSCDMA);

                        } else if (cellInfo instanceof CellInfoGsm) {
                            CellInfoGsm cellInfogsm = (CellInfoGsm) cellInfo;
                            CellSignalStrengthGsm cellSignalStrengthGsm = cellInfogsm.getCellSignalStrength();

                            level = cellSignalStrengthGsm.getLevel();
                            strength = cellSignalStrengthGsm.getDbm();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                                rssi = cellSignalStrengthGsm.getRssi();
                            }

                            rfChannelNo = cellInfogsm.getCellIdentity().getArfcn();
                            cellId = cellInfogsm.getCellIdentity().getCid();
                            locationAreaCode = cellInfogsm.getCellIdentity().getLac();
                            baseStationIdentityCode = cellInfogsm.getCellIdentity().getBsic();
                            mnc = cellInfogsm.getCellIdentity().getMncString();
                            mcc = cellInfogsm.getCellIdentity().getMccString();

//                            operatorName = cellInfogsm.getCellIdentity().getOperatorAlphaLong().toString();
//                            generation = context.getString(R.string.TWO_G);
                            technology = context.getString(R.string.GSM);


                        } else if (cellInfo instanceof CellInfoLte) {
                            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                            CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();

                            level = cellSignalStrengthLte.getLevel();
                            strength = cellSignalStrengthLte.getDbm();
                            rssi = cellSignalStrengthLte.getRssi();
                            rsrq = cellSignalStrengthLte.getRsrq();
                            rssnr = cellSignalStrengthLte.getRssnr();

                            rfChannelNo = cellInfoLte.getCellIdentity().getEarfcn();
                            cellId = cellInfoLte.getCellIdentity().getCi();
                            trackingAreaCode = cellInfoLte.getCellIdentity().getTac();
                            physicalCellId = cellInfoLte.getCellIdentity().getPci();
                            mnc = cellInfoLte.getCellIdentity().getMncString();
                            mcc = cellInfoLte.getCellIdentity().getMccString();

//                            operatorName = cellInfoLte.getCellIdentity().getOperatorAlphaLong().toString();
//                            generation = context.getString(R.string.FOUR_G);
                            technology = context.getString(R.string.LTE);

                        } else if (cellInfo instanceof CellInfoCdma) {
                            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                            CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();

                            strength = cellSignalStrengthCdma.getDbm();
                            level = cellSignalStrengthCdma.getLevel();
//                        rfChannelNo = cellInfoCdma.getCellIdentity().getNetworkId();
                            networkId = cellInfoCdma.getCellIdentity().getNetworkId();
                            baseStationIdentityCode = cellInfoCdma.getCellIdentity().getBasestationId();
                            systemId = cellInfoCdma.getCellIdentity().getSystemId();

//                            operatorName = cellInfoCdma.getCellIdentity().getOperatorAlphaLong().toString();
//                            generation = context.getString(R.string.TWO_G);
                            technology = context.getString(R.string.CDMA);

                        } else if (cellInfo instanceof CellInfoNr) {
                            CellInfoNr cellInfoNr = (CellInfoNr) cellInfo;
                            CellSignalStrengthNr cellSignalStrengthNr = (CellSignalStrengthNr) cellInfoNr.getCellSignalStrength();

                            CellIdentityNr cellIdentityNr = (CellIdentityNr) cellInfoNr.getCellIdentity();

                            level = cellSignalStrengthNr.getLevel();
                            rfChannelNo = cellIdentityNr.getNrarfcn();
                            cellId = cellIdentityNr.getNci();
                            physicalCellId = cellIdentityNr.getPci();
                            trackingAreaCode = cellIdentityNr.getTac();
                            mnc = cellIdentityNr.getMncString();
                            mcc = cellIdentityNr.getMccString();

                            strength = cellSignalStrengthNr.getDbm();
                            csi_rsrq = cellSignalStrengthNr.getCsiRsrq();
                            csi_rsrp = cellSignalStrengthNr.getCsiRsrp();
                            ss_rsrq = cellSignalStrengthNr.getSsRsrq();
                            ss_rsrp = cellSignalStrengthNr.getSsRsrp();

//                            operatorName = cellInfoNr.getCellIdentity().getOperatorAlphaLong().toString();
//                            generation = context.getString(R.string.FIVE_G);
                            technology = context.getString(R.string.NR);
                        }
                    }

                    SignalState signalState = new SignalState();
                    signalState.setSimIndex(simIndex);
//                    signalState.setOperatorName(operatorName);
                    signalState.setOperatorName(subscriptionInfo.getCarrierName().toString());
                    signalState.setSignalStrength(strength);
                    signalState.setChannelNo(rfChannelNo);
                    signalState.setTechnology(technology);
//                    signalState.setGeneration(generation);
                    signalState.setGeneration(networkTypeString);

                    signalState.setBaseStationIdentityCode(baseStationIdentityCode);
                    signalState.setCellId(cellId);
                    signalState.setLocationAreaCode(locationAreaCode);
                    signalState.setNetworkId(networkId);
                    signalState.setSystemId(systemId);
                    signalState.setPhysicalCellId(physicalCellId);
                    signalState.setTrackingAreaCode(trackingAreaCode);
                    signalState.setLevel(level);
                    signalState.setCpid(cpid);
                    signalState.setMobileCountryCode(mcc);
                    signalState.setMobileNetworkCode(mnc);

                    signalState.setRssi(rssi);
                    signalState.setRsrq(rsrq);
                    signalState.setCsi_rsrp(csi_rsrp);
                    signalState.setCsi_rsrq(csi_rsrq);
                    signalState.setSs_rsrp(ss_rsrp);
                    signalState.setSs_rsrq(ss_rsrq);
                    signalState.setRssnr(rssnr);

                    signalStates.add(signalState);
                }


            }

        }

        List<SignalState> signalStates_Min = signalStates.stream()
                .collect(Collectors.toMap(
                        SignalState::getSimIndex,
                        o -> o,
                        (o1, o2) -> o1.getSignalStrength() > o2.getSignalStrength() ? o1 : o2
                ))
                .values()
                .stream()
                .collect(Collectors.toList());

        return signalStates_Min;
    }


//    public static boolean isNRConnected(TelephonyManager telephonyManager) {
//        try {
//            Object obj = Class.forName(telephonyManager.getClass().getName())
//                    .getDeclaredMethod("getServiceState", new Class[0]).invoke(telephonyManager, new Object[0]);
//            // try extracting from string
//            String serviceState = obj.toString();
//            boolean is5gActive = serviceState.contains("nrState=CONNECTED") ||
//                    serviceState.contains("nsaState=5") ||
//                    (serviceState.contains("EnDc=true") &&
//                            serviceState.contains("5G Allocated=true"));
//            if (is5gActive) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    private static boolean is5GConnected(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//        Network activeNetwork = connectivityManager.getActiveNetwork();
//        if (activeNetwork != null) {
//            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
//            if (networkCapabilities != null) {
//                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) &&
//                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
//
//                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return false;
//                    }
//
//                    int networkType = telephonyManager.getNetworkType();
//                    if (networkType == TelephonyManager.NETWORK_TYPE_NR) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }


    private static String getNetworkTypeString(int networkType, Context context) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_NR:
                return context.getString(R.string.FIVE_G);
            case TelephonyManager.NETWORK_TYPE_LTE:
                return context.getString(R.string.FOUR_G);
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return context.getString(R.string.THREE_G);
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return context.getString(R.string.TWO_G);
            default:
                return "Unknown";
        }
    }

}