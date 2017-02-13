package pl.poblock.plan2fly.trips;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.ActivityUtils;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.tripdetail.DetailActivity;

import java.util.List;

public class TripFragment extends Fragment implements TripContract.View {

    private TripContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private View mProgressView;
    private View mFormView;
    private TextView skadTextView;
    private TextView dokadTextView;
    private RelativeLayout noInfo;
    private FrameLayout mProgressLayout;

    private PodrozItemListener listener = new PodrozItemListener() {
        @Override
        public void OnPodrozClick(int podrozPos) {
            mPresenter.openDetails(podrozPos);
        }
    };


    public TripFragment() {}

    public static TripFragment newInstance() {
        TripFragment fragment = new TripFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.flightsView);
        if(recyclerView!=null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        noInfo = (RelativeLayout) view.findViewById(R.id.noInfoLayout);

        mFormView = view.findViewById(R.id.trips_form);
        mProgressView = view.findViewById(R.id.trips_progress);
        mProgressLayout = (FrameLayout) view.findViewById(R.id.progressLayout);
        skadTextView = (TextView) view.findViewById(R.id.skadValue);
        dokadTextView = (TextView) view.findViewById(R.id.dokadValue);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showFlightDetailsUI(int podroz) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("pos",podroz);
        startActivity(intent);
    }

    @Override
    public void setPresenter(TripContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingError() {
        Snackbar.make(getView(),getString(R.string.blad),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressOnUI(boolean show) {
        mProgressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
        ActivityUtils.showProgress(show, getContext(), mFormView, mProgressView);
    }

    @Override
    public void showTripList(List<Podroz> data) {
        Log.i("Trip", "Show list : "+data);
        TripAdapter adapter = new TripAdapter(data, listener);
        if(recyclerView!=null) {
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
        }
        if(noInfo!=null) {
            noInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyResults() {
        if(recyclerView!=null) {
            recyclerView.setVisibility(View.GONE);
        }
        if(noInfo!=null) {
            noInfo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNames(String skad, String dokad) {
        skadTextView.setText(skad);
        dokadTextView.setText(dokad);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter!=null) {
            mPresenter.start();
        }
    }
}
