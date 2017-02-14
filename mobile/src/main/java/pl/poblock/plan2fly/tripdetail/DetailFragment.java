package pl.poblock.plan2fly.tripdetail;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.etap.SectionsPagerAdapter;

public class DetailFragment extends Fragment implements DetailContract.View {

    private ViewPager mViewPager;
    private TextView mViewCenaRazem;
    private DetailContract.Presenter mPresenter;

    public DetailFragment() {}

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewCenaRazem = (TextView) view.findViewById(R.id.detailCenaRazem);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter!=null) {
            mPresenter.start();
        }
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showTrip(int selectedPosition, Podroz podroz) {
        mViewCenaRazem.setText("Razem "+String.valueOf(podroz.getCenaRazem())+" zł");
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager(), selectedPosition);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public void showLoadingError() {
        Snackbar.make(getView(),getString(R.string.blad),Snackbar.LENGTH_LONG).show();
    }
}
