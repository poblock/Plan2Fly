package pl.poblock.plan2fly.data.repository.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.repository.MiastoRepository;

/**
 * Created by krzysztof.poblocki on 2017-02-14.
 */

public class MiastoLoader extends AsyncTaskLoader<List<Miasto>> {
    private Context context;

    public MiastoLoader(Context context) {
        super(context);
        this.context = context;
        onContentChanged();
    }

    @Override
    public List<Miasto> loadInBackground() {
        return MiastoRepository.getInstance(context).pobierzMiasta();
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
