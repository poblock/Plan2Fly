package pl.poblock.plan2fly.data.repository;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class SearchLoader extends AsyncTaskLoader<List<Podroz>> {
//        implements Repository.RepositoryObserver {

    private Query query;

    public SearchLoader(Context context) {
        super(context);
        onContentChanged();
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Query getQuery() {
        return query;
    }

    @Override
    public List<Podroz> loadInBackground() {
        if(query!=null) {
            return Repository.getInstance().pobierzPodroze(query);
        }
        return null;
    }

//    @Override
//    public void onPodrozChanged() {
//
//    }

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
