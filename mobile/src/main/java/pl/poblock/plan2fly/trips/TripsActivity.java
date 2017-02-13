package pl.poblock.plan2fly.trips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.ActivityUtils;
import pl.poblock.plan2fly.data.repository.PodrozRepository;
import pl.poblock.plan2fly.data.repository.Query;
import pl.poblock.plan2fly.data.repository.loaders.PodrozeLoader;
import pl.poblock.plan2fly.tripdetail.DetailActivity;

public class TripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TripFragment fragment = (TripFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment==null) {
            fragment = TripFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        int reload = getIntent().getIntExtra("reload", 0);
        PodrozeLoader loader = new PodrozeLoader(reload == 1, getApplicationContext());
        TripPresenter mPresenter = new TripPresenter(loader, getSupportLoaderManager(), fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("tryb","Ulubione");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
