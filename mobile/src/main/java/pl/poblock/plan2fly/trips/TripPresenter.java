package pl.poblock.plan2fly.trips;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.PodrozRepository;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class TripPresenter implements TripContract.Presenter {

    private final TripContract.View mView;
    private final LoaderManager mLoaderManager;
    private final PodrozRepository mPodrozRepository;

    public TripPresenter(PodrozRepository podrozRepository, LoaderManager supportLoaderManager,TripContract.View fragment) {
        this.mPodrozRepository = podrozRepository;
        this.mLoaderManager = supportLoaderManager;
        mView = fragment;
        mView.setPresenter(this);
    }

    private LoaderManager.LoaderCallbacks<List<Podroz>> searchCallback = new LoaderManager.LoaderCallbacks<List<Podroz>>() {
        @Override
        public Loader<List<Podroz>> onCreateLoader(int id, Bundle args) {
            showProgress(true);
            return mPodrozRepository;
        }

        @Override
        public void onLoadFinished(Loader<List<Podroz>> loader, List<Podroz> data) {
            showProgress(false);
            if(data!=null) {
                mView.showTripList(data);
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
