package com.dot.nbm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.dot.nbm.workers.NBMWorker;
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




        WorkRequest testRequest = new OneTimeWorkRequest.Builder(NBMWorker.class)
//                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(getApplicationContext()).enqueue(testRequest);

    }
}