package pl.poblock.plan2fly.etap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by krzysztof.poblocki on 2017-02-06.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private int mPodrozPosition;

    public SectionsPagerAdapter(FragmentManager fm, int position) {
        super(fm);
        this.mPodrozPosition = position;
    }

    @Override
    public Fragment getItem(int position) {
        return EtapFragment.newInstance(mPodrozPosition, position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
        }
        return null;
    }
}
