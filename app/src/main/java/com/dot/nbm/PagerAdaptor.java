package com.dot.nbm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdaptor extends FragmentStateAdapter {
    public PagerAdaptor(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new QoSFragment();
            case 1:
                return new AboutFragment();
            case 2:
                return new ContributionsFragment();
            case 3:
                return new BestOperatorFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
