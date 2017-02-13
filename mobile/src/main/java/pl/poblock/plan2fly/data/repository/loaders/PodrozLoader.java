package pl.poblock.plan2fly.data.repository.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.PodrozRepository;

/**
 * Created by krzysztof.poblocki on 2017-02-07.
 */

public class PodrozLoader extends AsyncTaskLoader<Podroz> {

    private int mPosition;

    public PodrozLoader(Context context, int position) {
        super(context);
        mPosition = position;
        onContentChanged();
    }

    @Override
    public Podroz loadInBackground() {
        return PodrozRepository.getInstance().getPodroz(mPosition);
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
