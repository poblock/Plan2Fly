package pl.poblock.plan2fly.trips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.ActivityUtils;

public class TripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        TripFragment fragment = (TripFragment) getSupportFragmentManager().findFragmentById(R.id.tripContainer);
        if(fragment==null) {
            String skad = getIntent().getStringExtra("skad");
            String dokad = getIntent().getStringExtra("dokad");
            fragment = TripFragment.newInstance(skad, dokad);
            ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
        TripPresenter mPresenter = new TripPresenter(fragment);
    }
}
