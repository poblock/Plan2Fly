package pl.poblock.plan2fly.trips;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.tripdetail.DetailActivity;

import java.util.List;

public class TripFragment extends Fragment implements TripContract.View {

    private static final String ARGUMENT_SKAD = "skad";
    private static final String ARGUMENT_DOKAD = "dokad";

    private TripContract.Presenter mPresenter;
    private String skad;
    private String dokad;

    private PodrozItemListener listener = new PodrozItemListener() {
        @Override
        public void OnPodrozClick(int podrozPos) {
            mPresenter.openDetails(podrozPos);
        }
    };

    public TripFragment() {}

    public static TripFragment newInstance(String skad, String dokad) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_SKAD, skad);
        arguments.putString(ARGUMENT_DOKAD, dokad);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.flightsView);
        if(recyclerView!=null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            TripAdapter adapter = new TripAdapter(listener);
            recyclerView.setAdapter(adapter);
        }

        TextView skadTextView = (TextView) view.findViewById(R.id.skadValue);
        TextView dokadTextView = (TextView) view.findViewById(R.id.dokadValue);
        skadTextView.setText(skad);
        dokadTextView.setText(dokad);
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
    public void showFlightsOnUI(List<Podroz> mValues) {
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
    public void onResume() {
        super.onResume();
        if(mPresenter!=null) {
            mPresenter.start();
        }
    }
}
