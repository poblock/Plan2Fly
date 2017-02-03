package pl.poblock.plan2fly.search;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.Query;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public interface SearchContract {
    interface View {
        void showLoadingError();
        void showProgressOnUI(final boolean show);
        void setPresenter(SearchContract.Presenter presenter);
        void prepareQuery(int month, int year);
        void monthChanged(int monthValue, int stringID, String yearTxt, int seekBarProgress);
        void setDane(List<String> collection);
    }

    interface Presenter {
        void showProgress(final boolean show);
        void performSearch();
        void start();
        void changeMonth(int i, int seekBarValue);
        void changeMonthFromSeekBar(int i, boolean fromUser);
    }
}
