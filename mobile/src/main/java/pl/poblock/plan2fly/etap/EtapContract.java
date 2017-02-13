package pl.poblock.plan2fly.etap;

/**
 * Created by krzysztof.poblocki on 2017-02-07.
 */

public interface EtapContract {
    interface View {
        void setPresenter(EtapContract.Presenter presenter);
//        void showLoadingError();
//        void showProgressOnUI(final boolean show);
//        void showTrip(Podroz data);
    }

    interface Presenter {
        void start();
//        void showProgress(final boolean show);
    }
}
