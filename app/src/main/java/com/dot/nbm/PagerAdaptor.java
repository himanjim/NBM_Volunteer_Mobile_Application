package com.dot.nbm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dot.nbm.fragments.AboutFragment;
import com.dot.nbm.fragments.QoSFragment;
import com.dot.nbm.fragments.SettingsFragment;

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
                return new SettingsFragment(); // status fragment
        }
        return new QoSFragment(); //chats fragment
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
