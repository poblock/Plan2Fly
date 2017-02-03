package pl.poblock.plan2fly.tripdetail;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.widget.TextView;

import pl.poblock.plan2fly.R;

public class DetailActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mPosition = getIntent().getIntExtra("pos",0);
//        Podroz podroz = Repository.getInstance().getCachedResult(mPosition);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        TextView txt = (TextView) findViewById(R.id.detailCenaRazem);
//        if(podroz!=null) {
//            txt.setText(String.valueOf(podroz.getCenaRazem())+" zł");
//        }
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        DetailPresenter mPresenter = new DetailPresenter();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return EtapFragment.newInstance(mPosition, position);
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
}
