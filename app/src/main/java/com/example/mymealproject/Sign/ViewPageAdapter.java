package com.example.mymealproject.Sign;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final  List<String> mTitleList = new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {

        super(fm);

    }

    @Override
    public Fragment getItem(int postion) {
        return mFragmentList.get(postion);
    }

    @Override
    public int getCount() {
        return  mTitleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);

    }
    public void AddFragment(Fragment fragment, String title ){
        mFragmentList.add(fragment);
        mTitleList.add(title);

    }
}

