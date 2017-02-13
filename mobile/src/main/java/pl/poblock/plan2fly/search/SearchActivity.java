package pl.poblock.plan2fly.search;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.common.ActivityUtils;
import pl.poblock.plan2fly.data.repository.MiastoRepository;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchFragment fragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(fragment==null) {
            fragment = SearchFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
        MiastoRepository miastoRepository = new MiastoRepository(getApplicationContext());
        SearchPresenter mPresenter = new SearchPresenter(miastoRepository, getSupportLoaderManager(), fragment);
    }
}

