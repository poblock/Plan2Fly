package pl.poblock.plan2fly.trips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.ActivityUtils;
import pl.poblock.plan2fly.data.repository.PodrozRepository;
import pl.poblock.plan2fly.data.repository.Query;

public class TripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        String skad = getIntent().getStringExtra("skad");
        String dokad = getIntent().getStringExtra("dokad");
        int miesiac = getIntent().getIntExtra("miesiac",-1);
        int rok = getIntent().getIntExtra("rok",-1);
        Query query = new Query(skad, dokad, miesiac, rok);

        TripFragment fragment = (TripFragment) getSupportFragmentManager().findFragmentById(R.id.tripContainer);
        if(fragment==null) {
            fragment = TripFragment.newInstance(skad, dokad, miesiac, rok);
            ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
        PodrozRepository podrozRepository = PodrozRepository.getInstance(getApplicationContext());
        podrozRepository.setQuery(query);
        TripPresenter mPresenter = new TripPresenter(podrozRepository, getSupportLoaderManager(), fragment);
    }
}
