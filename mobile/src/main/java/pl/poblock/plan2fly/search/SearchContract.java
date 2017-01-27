package pl.poblock.plan2fly.search;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.Query;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public interface SearchContract {
    interface View {
        void showProgressOnUI(final boolean show);
        void setPresenter(SearchContract.Presenter presenter);
        Query prepareQuery();
        void showTripList(String skad, String dokad);
        void monthChanged(int monthValue);
        void setMiastoAdapter(List<String> collection);
    }

    interface Presenter {
        void showProgress(final boolean show);
        void performSearch();
        void start();
        void changeMonth(int i);
    }
}
