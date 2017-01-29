package pl.poblock.plan2fly.trips;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import java.util.List;

import pl.poblock.plan2fly.data.model.Lot;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.Repository;
import pl.poblock.plan2fly.data.repository.SearchLoader;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class TripPresenter implements TripContract.Presenter {

    private final TripContract.View mView;
    public TripPresenter(TripContract.View fragment) {
        mView = fragment;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
//        List<Podroz> mValues = Repository.getInstance().getCachedResults();
//        mView.showFlightsOnUI(mValues);
    }

    @Override
    public void openDetails(int podroz) {
        mView.showFlightDetailsUI(podroz);
    }
}
