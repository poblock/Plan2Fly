package pl.poblock.plan2fly.data.repository;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import pl.poblock.plan2fly.data.model.Miasto;

/**
 * Created by krzysztof.poblocki on 2017-01-26.
 */

public class MiastoLoader extends AsyncTaskLoader<List<Miasto>> {
    public MiastoLoader(Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    public List<Miasto> loadInBackground() {
        return Repository.getInstance().pobierzMiasta();
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
