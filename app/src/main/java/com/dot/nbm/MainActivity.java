package com.dot.nbm;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.dot.nbm.workers.NBMWorker;
import com.dot.nbm.workers.NBMWorkerHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private String[] tab_labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab_labels = new String[]{getString(R.string.qos_tab_name), getString(R.string.about_us_tab_name), getString(R.string.setting_tab_name)};

        TabLayout tabLayout = findViewById(R.id.tabLayout);

//        TabItem qosTab = findViewById(R.id.qosTab);
//        TabItem aboutUsTab = findViewById(R.id.aboutUsTab);
//        TabItem contributionsTab = findViewById(R.id.contributionsTab);
//        TabItem bestOperatorTab = findViewById(R.id.bestOperatorTab);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);

        PagerAdaptor pagerAdaptor = new PagerAdaptor(this);
//        pagerAdaptor.addFragment(new QoSFragment());
//        pagerAdaptor.addFragment(new AboutFragment());
//        pagerAdaptor.addFragment(new ContributionsFragment());
//        pagerAdaptor.addFragment(new BestOperatorFragment());
        viewPager2.setAdapter(pagerAdaptor);

//        getSupportActionBar().setElevation(0);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tab_labels[position])).attach();
// set default position to 1 instead of default 0
        viewPager2.setCurrentItem(1, false);

//        Constraints constraints =  new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresDeviceIdle(true).build();

//        WorkRequest testRequest = new PeriodicWorkRequest()
        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            NBMWorkerHelper.doAllWork(getApplicationContext());

            Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

            PeriodicWorkRequest periodicNBMWork =
                    new PeriodicWorkRequest.Builder(NBMWorker.class, 15, TimeUnit.MINUTES)
                            .addTag(getString(R.string.worker_tag))
                            .setConstraints(constraints)
                            // setting a backoff on case the work needs to retry
                            .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                            .build();

            WorkManager.getInstance(getApplicationContext()).enqueueUniquePeriodicWork(
                    getString(R.string.worker_tag),
                    ExistingPeriodicWorkPolicy.KEEP, //Existing Periodic Work policy
                    periodicNBMWork //work request
            );


//            WorkRequest testRequest = new OneTimeWorkRequest.Builder(NBMWorker.class)
////                .setConstraints(constraints)
//                    .build();
//
//            WorkManager.getInstance(getApplicationContext()).enqueue(testRequest);

        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});
        }


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
}
