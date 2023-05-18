package com.dot.nbm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdaptor extends FragmentStateAdapter {

    public PagerAdaptor(FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new QoSFragment(); // calls fragment
            case 1:
                return new AboutFragment(); // chats fragment
            case 2:
                return new ContributionsFragment(); // status fragment
            case 3:
                return new BestOperatorFragment();
        }
        return new QoSFragment(); //chats fragment
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
