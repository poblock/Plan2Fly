package pl.poblock.plan2fly.trips;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.PodrozRepository;
import pl.poblock.plan2fly.data.repository.loaders.PodrozLoader;
import pl.poblock.plan2fly.data.repository.loaders.PodrozeLoader;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class TripPresenter implements TripContract.Presenter {

    private final TripContract.View mView;
    private final LoaderManager mLoaderManager;
    private final PodrozeLoader mLoader;
    private String mSkad;
    private String mDokad;

    public TripPresenter(Context context, PodrozeLoader loader, LoaderManager supportLoaderManager, TripContract.View fragment) {
        this.mLoaderManager = supportLoaderManager;
        this.mLoader = loader;
        mView = fragment;
        mView.setPresenter(this);
        this.mSkad = PodrozRepository.getInstance(context).getQuery().getSkadFull();
        this.mDokad = PodrozRepository.getInstance(context).getQuery().getDokadFull();
    }

    private LoaderManager.LoaderCallbacks<List<Podroz>> searchCallback = new LoaderManager.LoaderCallbacks<List<Podroz>>() {
        @Override
        public Loader<List<Podroz>> onCreateLoader(int id, Bundle args) {
            showProgress(true);
            return mLoader;
        }

        @Override
        public void onLoadFinished(Loader<List<Podroz>> loader, List<Podroz> data) {
            showProgress(false);
            mView.showNames(mSkad, mDokad);
            if(data!=null) {
                if(data.isEmpty()) {
                    mView.showEmptyResults();
                } else {
                    mView.showTripList(data);
                }
            } else {
                mView.showLoadingError();
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Podroz>> loader) {}
    };

    @Override
    public void start() {
        mLoaderManager.initLoader(1, null, searchCallback);
    }

    @Override
    public void openDetails(int podroz) {
        mView.showFlightDetailsUI(podroz);
    }

    @Override
    public void showProgress(boolean show) {
        mView.showProgressOnUI(show);
    }
}
