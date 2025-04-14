package com.tools.edge.dynamic.island.ui.component.adaptar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ViewPagerAdapter extends FragmentStateAdapter {
    public int getItemCount() {
        return 1;
    }

    public ViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }

//    public Fragment createFragment(int i) {
//        if (i == 0) {
//            return new SettingsFragment();
//        }
//        if (i != 1) {
//            return new SettingsFragment();
//        }
//        return ThemeFragment.newInstance(Constants.GETTING_APP, 3);
//    }
}
