package pl.poblock.plan2fly.tripdetail;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.PodrozRepository;
import pl.poblock.plan2fly.data.repository.loaders.PodrozLoader;

/**
 * Created by krzysztof.poblocki on 2017-02-02.
 */

public class DetailPresenter implements DetailContract.Presenter {

    private PodrozLoader mLoader;
    private final LoaderManager mLoaderManager;
    private final DetailContract.View mView;
    private int mPosition;

    public DetailPresenter(PodrozLoader loader, int position, LoaderManager supportLoaderManager, DetailContract.View view) {
        this.mPosition = position;
        this.mLoader = loader;
        this.mLoaderManager = supportLoaderManager;
        this.mView = view;
        mView.setPresenter(this);
    }

    private LoaderManager.LoaderCallbacks<Podroz> searchCallback = new LoaderManager.LoaderCallbacks<Podroz>() {
        @Override
        public Loader<Podroz> onCreateLoader(int id, Bundle args) {
//            showProgress(true);
            return mLoader;
        }

        @Override
        public void onLoadFinished(Loader<Podroz> loader, Podroz podroz) {
//            showProgress(false);
            if(podroz!=null) {
                mView.showTrip(mPosition, podroz);
            } else {
                mView.showLoadingError();
            }
        }

        @Override
        public void onLoaderReset(Loader<Podroz> loader) {}
    };

    @Override
    public void start() {
        mLoaderManager.initLoader(3, null, searchCallback);
    }

}
