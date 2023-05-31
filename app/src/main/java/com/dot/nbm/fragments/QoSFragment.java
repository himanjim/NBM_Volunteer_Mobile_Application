package com.dot.nbm.fragments;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
//            requestPermissionLauncher.launch(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});
        }



        return layout;
    }

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


}