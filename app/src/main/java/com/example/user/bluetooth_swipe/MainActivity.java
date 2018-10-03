package com.example.user.bluetooth_swipe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SectionPagerAdapter mSectionPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpage);
        mViewPager.setAdapter(mSectionPagerAdapter);
    }

    private class SectionPagerAdapter extends FragmentPagerAdapter
    {
        public SectionPagerAdapter (FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment = null;
            switch (position)
            {
                case 0:
                    fragment = new bluetooth();
                    break;
                case 1:
                    fragment = new clock();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "bluetooth";
                case 1:
                    return "clock";
                default:
                    return null;
            }
        }
    }


}
