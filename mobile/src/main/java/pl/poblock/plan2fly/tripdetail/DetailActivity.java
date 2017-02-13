package pl.poblock.plan2fly.tripdetail;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.ActivityUtils;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.PodrozRepository;
import pl.poblock.plan2fly.data.repository.loaders.PodrozLoader;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment==null) {
            fragment = DetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        String tryb = getIntent().getStringExtra("tryb");
        int mPosition = -1;
        if(tryb!=null && tryb.equals("Ulubione")) {
            mPosition = 999;
        } else {
            mPosition = getIntent().getIntExtra("pos", 0);
        }
        PodrozLoader loader = new PodrozLoader(getApplicationContext(), mPosition);
        DetailPresenter presenter = new DetailPresenter(loader, mPosition, getSupportLoaderManager(), fragment);
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

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
