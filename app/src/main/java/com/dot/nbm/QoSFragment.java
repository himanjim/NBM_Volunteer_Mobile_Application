package com.dot.nbm;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class QoSFragment extends Fragment {

    MainActivityViewModel mainActivityViewModel;

    private FusedLocationProviderClient fusedLocationClient;



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.d("signalStrength", getSignalStrength(getContext()));

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_qo_s,container,false);

        TextView operatorNameTextView = layout.findViewById(R.id.operatorNameTextView);
        TextView technologyTextView = layout.findViewById(R.id.technologyTextView);
        TextView signalStrengthTextView = layout.findViewById(R.id.signalStrengthTextView);

        if (ContextCompat.checkSelfPermission(
                getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            MainActivityViewModel signalViewModel = getSignalStrength(getContext());
            operatorNameTextView.setText(signalViewModel.getOperator());
            technologyTextView.setText(signalViewModel.getTechnology());
            signalStrengthTextView.setText(String.valueOf(signalViewModel.getSignalStrength()));

            Log.d("signalStrengthCaptured", String.valueOf(signalViewModel.getSignalStrength()));
//            Log.d("locationCaptured", fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null));
        }
//        else if (shouldShowRequestPermissionRationale()) {
//            // In an educational UI, explain to the user why your app requires this
//            // permission for a specific feature to behave as expected, and what
//            // features are disabled if it's declined. In this UI, include a
//            // "cancel" or "no thanks" button that lets the user continue
//            // using your app without granting the permission.
//            showInContextUI(...);
//        }
        else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});
        }


        return inflater.inflate(R.layout.fragment_qo_s, container, false);
    }

    private ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.get(
                                android.Manifest.permission.ACCESS_FINE_LOCATION);
                        Boolean coarseLocationGranted = result.get(
                                android.Manifest.permission.ACCESS_COARSE_LOCATION);
                        if (fineLocationGranted != null && fineLocationGranted) {
                            // Precise location access granted.
                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                            // Only approximate location access granted.
                        } else {
                            // No location access granted.
                        }
                    }
            );


    // Register the permissions callback, which handles the user's response to the
// system permissions dialog. Save the return value, an instance of
// ActivityResultLauncher, as an instance variable.
//    private ActivityResultLauncher<String> requestPermissionLauncher =
//            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
//                if (isGranted) {
//                    Log.d("signalStrengthCaptured", getSignalStrength(getContext()));
//                } else {
//                    // Explain to the user that the feature is unavailable because the
//                    // features requires a permission that the user has denied. At the
//                    // same time, respect the user's decision. Don't link to system
//                    // settings in an effort to convince the user to change their
//                    // decision.
//                }
//            });

    private static MainActivityViewModel getSignalStrength(Context context) throws SecurityException {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String strength = null;
        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();   //This will give info of all sims present inside your mobile
        MainActivityViewModel signalViewModel = null;
        if(cellInfos != null) {
            signalViewModel = new MainActivityViewModel();
            Log.d("cellInfos", cellInfos.toString());
            for (int i = 0 ; i < cellInfos.size() ; i++) {
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

                        signalViewModel.setSignalStrength(cellSignalStrengthLte.getDbm());
                        signalViewModel.setOperator((String) cellInfoLte.getCellIdentity().getMobileNetworkOperator());
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
}