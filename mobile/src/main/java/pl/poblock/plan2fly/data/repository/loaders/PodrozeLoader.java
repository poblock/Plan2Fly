package pl.poblock.plan2fly.data.repository.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.PodrozRepository;

/**
 * Created by krzysztof.poblocki on 2017-02-07.
 */

public class PodrozeLoader extends AsyncTaskLoader<List<Podroz>> {
    private boolean mForce;

    public PodrozeLoader(boolean force, Context context) {
        super(context);
        this.mForce = force;
        onContentChanged();
    }

    @Override
    public List<Podroz> loadInBackground() {
        return PodrozRepository.getInstance().pobierzPodroze(mForce);
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
