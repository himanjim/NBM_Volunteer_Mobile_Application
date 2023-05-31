package com.dot.nbm.workers;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.work.ListenableWorker;

import com.dot.nbm.doers.GsonHandler;
import com.dot.nbm.doers.NetworkStateFetcher;
import com.dot.nbm.doers.SignalStateFetcher;
import com.dot.nbm.model.CombinedSignalNetworkHardwareState;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class NBMWorkerHelper {

    public static ListenableWorker.Result doAllWork(Context applicationContext) {

        CombinedSignalNetworkHardwareState combinedSignalNetworkHardwareState = new CombinedSignalNetworkHardwareState();

        if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return ListenableWorker.Result.failure();
        }
        else{
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext);
            fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null).addOnSuccessListener(location -> {
                combinedSignalNetworkHardwareState.setLatitude(location.getLatitude());
                combinedSignalNetworkHardwareState.setLongitude(location.getLongitude());
            });
        }

        combinedSignalNetworkHardwareState.setSignalStates(SignalStateFetcher.getSignalState(applicationContext));

        combinedSignalNetworkHardwareState.setNetworkStates(NetworkStateFetcher.getNetworkState(applicationContext));

        combinedSignalNetworkHardwareState.setManufacturer(Build.MANUFACTURER);
        combinedSignalNetworkHardwareState.setProduct(Build.PRODUCT);
        combinedSignalNetworkHardwareState.setBrand(Build.BRAND);
        combinedSignalNetworkHardwareState.setModel(Build.MODEL);
        combinedSignalNetworkHardwareState.setHost(Build.HOST);
        combinedSignalNetworkHardwareState.setHardware(Build.HARDWARE);

        Log.i("combinedSignalNetworkHardwareState", combinedSignalNetworkHardwareState.toString());

        List<CombinedSignalNetworkHardwareState> combinedSignalNetworkHardwareStates = new ArrayList<>();
        combinedSignalNetworkHardwareStates.add(combinedSignalNetworkHardwareState);

        GsonHandler.saveCombinedSignalNetworkHardwareStates(applicationContext, combinedSignalNetworkHardwareStates);

        return ListenableWorker.Result.success();

    }
}
