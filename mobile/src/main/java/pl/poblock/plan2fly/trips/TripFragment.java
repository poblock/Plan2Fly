package pl.poblock.plan2fly.trips;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.ActivityUtils;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.tripdetail.DetailActivity;

import java.util.List;

public class TripFragment extends Fragment implements TripContract.View {

    private static final String ARGUMENT_SKAD = "skad";
    private static final String ARGUMENT_DOKAD = "dokad";
    private static final String ARGUMENT_MIESIAC = "miesiac";
    private static final String ARGUMENT_ROK = "rok";

    private TripContract.Presenter mPresenter;
    private String skad;
    private String dokad;
    private int miesiac;
    private int rok;

    private RecyclerView recyclerView;
    private View mProgressView;
    private View mFormView;

    private PodrozItemListener listener = new PodrozItemListener() {
        @Override
        public void OnPodrozClick(int podrozPos) {
            mPresenter.openDetails(podrozPos);
        }
    };

    public TripFragment() {}

    public static TripFragment newInstance(String skad, String dokad, int miesiac, int rok) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_SKAD, skad);
        arguments.putString(ARGUMENT_DOKAD, dokad);
        arguments.putInt(ARGUMENT_MIESIAC, miesiac);
        arguments.putInt(ARGUMENT_ROK, rok);
        TripFragment fragment = new TripFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            skad = getArguments().getString(ARGUMENT_SKAD);
            dokad = getArguments().getString(ARGUMENT_DOKAD);
            miesiac = getArguments().getInt(ARGUMENT_MIESIAC);
            rok = getArguments().getInt(ARGUMENT_ROK);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.flightsView);
        if(recyclerView!=null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //TripAdapter adapter = new TripAdapter(listener);
            //recyclerView.setAdapter(adapter);
        }
        mFormView = view.findViewById(R.id.trips_form);
        mProgressView = view.findViewById(R.id.trips_progress);

        TextView skadTextView = (TextView) view.findViewById(R.id.skadValue);
        TextView dokadTextView = (TextView) view.findViewById(R.id.dokadValue);
        skadTextView.setText(skad.substring(0, skad.length()-3));
        dokadTextView.setText(dokad.substring(0, dokad.length()-3));
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
        ActivityUtils.showProgress(show, getContext(), mFormView, mProgressView);
    }

    @Override
    public void showTripList(List<Podroz> data) {
        TripAdapter adapter = new TripAdapter(data, listener);
        if(recyclerView!=null) {
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter!=null) {
            mPresenter.start();
        }
    }
}
