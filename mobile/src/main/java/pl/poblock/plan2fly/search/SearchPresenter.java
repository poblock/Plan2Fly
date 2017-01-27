package pl.poblock.plan2fly.search;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.LinkedList;
import java.util.List;

import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.MiastoLoader;
import pl.poblock.plan2fly.data.repository.Query;
import pl.poblock.plan2fly.data.repository.Repository;
import pl.poblock.plan2fly.data.repository.SearchLoader;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private final SearchContract.View mView;
    private final SearchLoader mSearchLoader;
    private final MiastoLoader mMiastoLoader;
    private final LoaderManager mLoaderManager;

    private LoaderManager.LoaderCallbacks<List<Miasto>> miastoCallback = new LoaderManager.LoaderCallbacks<List<Miasto>>() {
        @Override
        public Loader<List<Miasto>> onCreateLoader(int id, Bundle args) {
            showProgress(true);
            return mMiastoLoader;
        }

        @Override
        public void onLoadFinished(Loader<List<Miasto>> loader, List<Miasto> data) {
            showProgress(false);
            if(data!=null) {
                Repository.getInstance().setCachedMiasta(data);
                List<String> collection = new LinkedList<>();
                for(Miasto m : data) {
                    collection.add(m.getAutoCompleteLabel());
                }
                mView.setMiastoAdapter(collection);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Miasto>> loader) {}
    };

    private LoaderManager.LoaderCallbacks<List<Podroz>> searchCallback = new LoaderManager.LoaderCallbacks<List<Podroz>>() {
        @Override
        public Loader<List<Podroz>> onCreateLoader(int id, Bundle args) {
            showProgress(true);
            return mSearchLoader;
        }

        @Override
        public void onLoadFinished(Loader<List<Podroz>> loader, List<Podroz> data) {
            showProgress(false);
            if(data!=null) {
                Repository.getInstance().setCachedResults(data);
                Query q = mSearchLoader.getQuery();
                mView.showTripList(q.getSkad(), q.getDokad());
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Podroz>> loader) {}
    };


    public SearchPresenter(SearchLoader searchLoader, MiastoLoader miastoLoader, LoaderManager supportLoaderManager, SearchContract.View fragment) {
        this.mSearchLoader = searchLoader;
        this.mMiastoLoader = miastoLoader;
        this.mLoaderManager = supportLoaderManager;
        this.mView = fragment;
        this.mView.setPresenter(this);
    }

    @Override
    public void showProgress(boolean show) {
        mView.showProgressOnUI(show);
    }

    @Override
    public void performSearch() {
        if(validate()) {
            mLoaderManager.initLoader(1, null, searchCallback);
        }
    }

    @Override
    public void start() { // TODO
        mLoaderManager.initLoader(2, null, miastoCallback);
    }

    @Override
    public void changeMonth(int i) {
        mView.monthChanged(i+1);
    }

    public boolean validate() {
        boolean result = false;
        Query query = mView.prepareQuery();
        if(query!=null) {
            mSearchLoader.setQuery(query);
            result = true;
        }
        return result;
    }
}
