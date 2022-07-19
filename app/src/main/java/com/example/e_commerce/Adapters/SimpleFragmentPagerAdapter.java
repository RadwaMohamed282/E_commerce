package com.example.e_commerce.Adapters;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.e_commerce.Fragments.CameraFragment;
import com.example.e_commerce.Fragments.LabtopFragment;
import com.example.e_commerce.Fragments.MobileFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private int numoftabs;
    public SimpleFragmentPagerAdapter(FragmentManager fm, int numoftabs) {
        super(fm);
        this.numoftabs = numoftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new MobileFragment();
            case 1:
                return new CameraFragment();
            case 2:
                return new LabtopFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Mobile";
            case 1:
                return "Camera";
            case 2:
                return "Laptop";
        }
        return super.getPageTitle(position);
    }
}
