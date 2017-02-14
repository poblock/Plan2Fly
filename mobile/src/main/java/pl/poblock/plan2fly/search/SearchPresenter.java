package pl.poblock.plan2fly.search;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import pl.poblock.plan2fly.R;
import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.repository.MiastoRepository;
import pl.poblock.plan2fly.data.repository.loaders.MiastoLoader;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private final SearchContract.View mView;
    private final LoaderManager mLoaderManager;
    private final MiastoLoader mMiastoLoader;
    private Calendar calendar;
    private Calendar calendarMin;
    private Calendar calendarMax;
    private int currSeekBarProgress;

    public SearchPresenter(MiastoLoader loader, LoaderManager supportLoaderManager, SearchContract.View fragment) {
        this.mLoaderManager = supportLoaderManager;
        this.mView = fragment;
        this.mView.setPresenter(this);
        this.mMiastoLoader = loader;
    }

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
                List<String> collection = new LinkedList<>();
                for(Miasto m : data) {
                    collection.add(m.getAutoCompleteLabel());
                }
                mView.setDane(collection);
                prepareCalendars();
            } else {
                mView.showLoadingError();
            }
        }

        @Override
        public void onLoaderReset(Loader<List<Miasto>> loader) {}
    };

    @Override
    public void showProgress(boolean show) {
        mView.showProgressOnUI(show);
    }

    @Override
    public void performSearch() {
        if(calendar!=null) {
            mView.prepareQuery(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
        }
    }

    @Override
    public void start() {
        mLoaderManager.initLoader(2, null, miastoCallback);
    }

    @Override
    public void changeMonth(int stepMonthValue, int seekBarValue) {
        if(calendar!=null) {
            if (verify(stepMonthValue)) {
                calendar.add(Calendar.MONTH, stepMonthValue);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                int seekBarProgress = -1;
                if(seekBarValue!=Integer.MIN_VALUE) {
                    seekBarProgress = seekBarValue;
                } else {
                    seekBarProgress = (stepMonthValue > 0 ?  currSeekBarProgress + 1 :  currSeekBarProgress - 1);
                }
                mView.monthChanged(stepMonthValue, getStringIDForMonth(calendar.get(Calendar.MONTH)), sdf.format(calendar.getTime()), seekBarProgress);
            }
        }
    }

    @Override
    public void changeMonthFromSeekBar(int seekBarProgress, boolean fromUser) {
        if(fromUser) {
            changeMonth(seekBarProgress-currSeekBarProgress, seekBarProgress);
        }
        currSeekBarProgress = seekBarProgress;
    }

    private int getStringIDForMonth(int month) {
        int text = -1;
        switch (month) {
            case 0 : text = R.string.january; break;
            case 1 : text = R.string.february; break;
            case 2 : text = R.string.march; break;
            case 3 : text = R.string.april; break;
            case 4 : text = R.string.may; break;
            case 5 : text = R.string.june; break;
            case 6 : text = R.string.july; break;
            case 7 : text = R.string.august; break;
            case 8 : text = R.string.september; break;
            case 9 : text = R.string.october; break;
            case 10 : text = R.string.november; break;
            case 11 : text = R.string.december; break;
        }
        return text;
    }

    private void prepareCalendars() {
        calendarMin = Calendar.getInstance();
        calendar = Calendar.getInstance();
        calendarMax = Calendar.getInstance();
        calendarMax.add(Calendar.YEAR, 1);
        currSeekBarProgress = 0;
        changeMonth(0, Integer.MIN_VALUE);
    }

    private boolean verify(int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(calendar.getTime());
        c.add(Calendar.MONTH, month);
        return c.compareTo(calendarMin) >= 0 && c.compareTo(calendarMax) < 0;
    }
}
