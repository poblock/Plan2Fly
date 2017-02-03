package pl.poblock.plan2fly.trips;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public interface TripContract {
    interface View {
        void showFlightDetailsUI(int podroz);
        void setPresenter(TripContract.Presenter presenter);
        void showLoadingError();
        void showProgressOnUI(final boolean show);
        void showTripList(List<Podroz> data);
    }

    interface Presenter {
        void start();
        void openDetails(int podroz);
        void showProgress(final boolean show);
    }
}
