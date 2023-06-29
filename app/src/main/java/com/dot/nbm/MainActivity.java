package com.dot.nbm;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.text.LineBreaker;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.dot.nbm.doers.MainActivityHelper;
import com.dot.nbm.doers.TestGsonHandler;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private String[] tab_labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab_labels = new String[]{getString(R.string.qos_tab_name), getString(R.string.about_us_tab_name), getString(R.string.setting_tab_name)};

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setInlineLabel(true);

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

        int[] tabIcons = {
                R.mipmap.signal_tab,
                R.mipmap.about_us_tab,
                R.mipmap.settings_tab
        };

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(tab_labels[position]);
            tab.setIcon(tabIcons[position]);
        }).attach();
// set default position to 1 instead of default 0
        viewPager2.setCurrentItem(1, false);

//        Constraints constraints =  new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresDeviceIdle(true).build();

//        WorkRequest testRequest = new PeriodicWorkRequest()
        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            MainActivityHelper.runScheduleWorker(getApplicationContext());
//            WorkRequest testRequest = new OneTimeWorkRequest.Builder(NBMWorker.class)
////                .setConstraints(constraints)
//                    .build();
//
//            WorkManager.getInstance(getApplicationContext()).enqueue(testRequest);

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.permission_alert_title);
// Add the buttons
            builder.setPositiveButton(R.string.permission_alert_ok, (dialog, id) -> requestPermissionLauncher.launch(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}));
            TextView msg = new TextView(this);
            msg.setText(R.string.permission_alert_msg);
            msg.setPadding(20, 20, 20, 20);
            msg.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            builder.setView(msg);
            AlertDialog dialog = builder.create();
            dialog.show();
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
//            new NBMPermissionDialogFragment().show(getSupportFragmentManager(), getString(R.string.NBM_P_F_Tag));
//            requestPermissionLauncher.launch(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});
        }

//        if (ContextCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) !=
//                PackageManager.PERMISSION_GRANTED) {
//            requestPermissionLauncher2.launch(new String[]{android.Manifest.permission.ACCESS_BACKGROUND_LOCATION});
//        }

        Integer contributions = TestGsonHandler.getContributionCount(getApplicationContext());
        Log.i("combinedSignalNetworkHardwareState", "MATestcontributions" + contributions);
    }

    private final ActivityResultLauncher<String[]> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.get(
                                android.Manifest.permission.ACCESS_FINE_LOCATION);
                        Boolean coarseLocationGranted = result.get(
                                android.Manifest.permission.ACCESS_COARSE_LOCATION);
                        Log.i("combinedSignalNetworkHardwareState", "inside permission launcher");
                        if ((fineLocationGranted != null && fineLocationGranted) || (coarseLocationGranted != null && coarseLocationGranted)) {
                            Log.i("combinedSignalNetworkHardwareState", "After alert got permission");
                            MainActivityHelper.runScheduleWorker(getApplicationContext());

                            Log.i("combinedSignalNetworkHardwareState", "Ran activity helper");
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage(R.string.permission_alert_msg_again)
                                    .setTitle(R.string.permission_alert_title_again);
// Add the buttons
                            builder.setPositiveButton(R.string.permission_alert_ok_again, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    requestPermissionLauncher.launch(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});
                                }
                            });
                            builder.setNegativeButton(R.string.permission_alert_no_again, (dialog, id) -> {
                                this.finish();
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
            );

//    private final ActivityResultLauncher<String[]> requestPermissionLauncher2 =
//            registerForActivityResult(new ActivityResultContracts
//                            .RequestMultiplePermissions(), result -> {
//                        Boolean backgroundLocationGranted = result.get(
//                                Manifest.permission.ACCESS_BACKGROUND_LOCATION);
//                        Log.i("combinedSignalNetworkHardwareState", "inside permission launcher");
//                        if ((backgroundLocationGranted != null && backgroundLocationGranted) ) {
//                            Log.i("combinedSignalNetworkHardwareState", "Got bg permission");
//                        }
//                    }
//            );

}
