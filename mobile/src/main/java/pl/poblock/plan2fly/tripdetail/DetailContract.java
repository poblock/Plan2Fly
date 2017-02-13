package pl.poblock.plan2fly.tripdetail;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;

/**
 * Created by krzysztof.poblocki on 2017-02-02.
 */

public interface DetailContract {
    interface View {
        void setPresenter(DetailContract.Presenter presenter);
        void showTrip(int selectedPosition, Podroz data);
        void showLoadingError();
    }

    interface Presenter {
        void start();
    }
}
