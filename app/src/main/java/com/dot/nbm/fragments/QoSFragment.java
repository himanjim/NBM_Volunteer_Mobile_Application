package com.dot.nbm.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import androidx.lifecycle.ViewModelProvider;

import com.dot.nbm.R;
import com.dot.nbm.doers.SignalStateFetcher;
import com.dot.nbm.model.MainActivityViewModel;
import com.dot.nbm.model.SignalState;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class QoSFragment extends Fragment {

    MainActivityViewModel mainActivityViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.d("signalStrength", getSignalStrength(getContext()));

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_qo_s, container, false);

        TextView operatorNameTextView = layout.findViewById(R.id.operatorNameTextView);
        TextView technologyTextView = layout.findViewById(R.id.technologyTextView);
        TextView signalStrengthTextView = layout.findViewById(R.id.signalStrengthTextView);
//        signalStrengthTextView.setText("Hello");
//        Log.d("signalStrengthTextView", (String) operatorNameTextView.getText());

        Log.d("Phone_Model", String.join("-",Build.MANUFACTURER, Build.PRODUCT, Build.BRAND, Build.MODEL, Build.HOST, Build.HARDWARE ));

        if (ContextCompat.checkSelfPermission(
                getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            List<SignalState> signalStates = SignalStateFetcher.getSignalState(getContext());

            SignalState firstSignalState = signalStates.get(0);

            mainActivityViewModel.setTechnology(firstSignalState.getTechnology());
            mainActivityViewModel.setOperatorName(firstSignalState.getOperaterName());
            mainActivityViewModel.setSignalStrength(firstSignalState.getSignalStrength());

            operatorNameTextView.setText(mainActivityViewModel.getOperatorName());
            technologyTextView.setText(mainActivityViewModel.getTechnology());
            signalStrengthTextView.setText(String.valueOf(mainActivityViewModel.getSignalStrength()));

//            Log.d("signalStrengthCaptured", String.valueOf(signalViewModel.getSignalStrength()));
//            Log.d("locationCaptured", fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null));
        }
        if (ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_BACKGROUND_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher2.launch(new String[]{android.Manifest.permission.ACCESS_BACKGROUND_LOCATION});
        }


        return layout;
    }

    private final ActivityResultLauncher<String[]> requestPermissionLauncher2 =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean backgroundLocationGranted = result.get(
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION);
                        Log.i("combinedSignalNetworkHardwareState", "inside permission launcher");
                        if ((backgroundLocationGranted != null && backgroundLocationGranted) ) {
                            Log.i("combinedSignalNetworkHardwareState", "Got bg permission");
                        }
                    }
            );

}