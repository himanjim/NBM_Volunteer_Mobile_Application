package com.dot.nbm.workers;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.app.ActivityCompat;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

import com.dot.nbm.doers.GsonHandler;
import com.dot.nbm.doers.NetworkStateFetcher;
import com.dot.nbm.doers.SignalStateFetcher;
import com.dot.nbm.doers.TestGsonHandler;
import com.dot.nbm.model.CombinedSignalNetworkHardwareState;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;

public class NBMListenableWorker extends ListenableWorker {
    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    public NBMListenableWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        Log.i("combinedSignalNetworkHardwareState", "started Listenable NBM worker");
        Context applicationContext = getApplicationContext();

        Integer contributions = TestGsonHandler.getContributionCount(applicationContext);

        if (contributions == null)
            contributions = 0;

//        Log.i("combinedSignalNetworkLocation", "contributions" + contributions);

        TestGsonHandler.incrementContributionCount(applicationContext, contributions + 1);

        return CallbackToFutureAdapter.getFuture(completer -> {
            getLocation(completer, applicationContext);
            return completer;
        });
    }

    private void getLocation(CallbackToFutureAdapter.Completer<Result> completer, Context applicationContext) {
        Log.i("combinedSignalNetworkHardwareState", "started NBM Listenable worker Helper");

        CombinedSignalNetworkHardwareState combinedSignalNetworkHardwareState = new CombinedSignalNetworkHardwareState();

        if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("combinedSignalNetworkLocation", "No location access");
            completer.set(Result.failure());
        } else {
            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext);
            LocationCallback mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                }
            };

            LocationRequest mLocationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
                    .setWaitForAccurateLocation(true)
                    .setMinUpdateIntervalMillis(5000)
                    .setMaxUpdateDelayMillis(100000)
                    .build();

            mFusedLocationClient
                    .getCurrentLocation(PRIORITY_HIGH_ACCURACY, null)
                    .addOnCompleteListener(new OnCompleteListener<Location>() {

                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                Location mLocation = task.getResult();
                                Log.d("combinedSignalNetworkLocation", "Location : " + mLocation);
                                combinedSignalNetworkHardwareState.setLatitude(mLocation.getLatitude());
                                combinedSignalNetworkHardwareState.setLongitude(mLocation.getLongitude());

                                combinedSignalNetworkHardwareState.setSignalStates(SignalStateFetcher.getSignalState(applicationContext));

                                combinedSignalNetworkHardwareState.setNetworkStates(NetworkStateFetcher.getNetworkState(applicationContext));

                                combinedSignalNetworkHardwareState.setManufacturer(Build.MANUFACTURER);
                                combinedSignalNetworkHardwareState.setProduct(Build.PRODUCT);
                                combinedSignalNetworkHardwareState.setBrand(Build.BRAND);
                                combinedSignalNetworkHardwareState.setModel(Build.MODEL);
                                combinedSignalNetworkHardwareState.setHost(Build.HOST);
                                combinedSignalNetworkHardwareState.setHardware(Build.HARDWARE);
                                combinedSignalNetworkHardwareState.setTimeStamp(System.currentTimeMillis());


                                Log.i("combinedSignalNetworkHardwareState", combinedSignalNetworkHardwareState.toString());

                                List<CombinedSignalNetworkHardwareState> combinedSignalNetworkHardwareStates = GsonHandler.getCombinedSignalNetworkHardwareStates(applicationContext);

                                if (combinedSignalNetworkHardwareStates == null) {
                                    combinedSignalNetworkHardwareStates = new ArrayList<>();
                                }
                                Log.i("combinedSignalNetworkHardwareStates size", combinedSignalNetworkHardwareStates.size() + "");
                                Log.i("combinedSignalNetworkHardwareStates", combinedSignalNetworkHardwareStates.toString());

                                combinedSignalNetworkHardwareStates.add(combinedSignalNetworkHardwareState);

                                GsonHandler.saveCombinedSignalNetworkHardwareStates(applicationContext, combinedSignalNetworkHardwareStates);

                                Integer contributions = GsonHandler.getContributionCount(applicationContext);

                                if (contributions == null)
                                    contributions = 0;

                                Log.i("combinedSignalNetworkHardwareState", "contributions" + contributions);

                                GsonHandler.incrementContributionCount(applicationContext, contributions + 1);

                                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                                completer.set(Result.success());
                            } else {
                                Log.w("combinedSignalNetworkHardwareState", "Failed to get location.");
                                completer.set(Result.failure());
                            }
                        }
                    });

            mFusedLocationClient.requestLocationUpdates(mLocationRequest, null);

        }
    }
}
