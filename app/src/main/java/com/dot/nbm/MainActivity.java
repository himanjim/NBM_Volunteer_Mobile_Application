package com.dot.nbm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = findViewById(R.id.tabLayout);

        TabItem qosTab = findViewById(R.id.qosTab);
        TabItem aboutUsTab = findViewById(R.id.aboutUsTab);
        TabItem contributionsTab = findViewById(R.id.contributionsTab);
        TabItem bestOperatorTab = findViewById(R.id.bestOperatorTab);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);

        PagerAdaptor pagerAdaptor = new PagerAdaptor(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(pagerAdaptor);
    }

}