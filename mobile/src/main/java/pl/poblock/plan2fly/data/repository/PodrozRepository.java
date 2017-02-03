package pl.poblock.plan2fly.data.repository;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.LinkedList;
import java.util.List;

import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.service.Service;

/**
 * Created by krzysztof.poblocki on 2017-02-01.
 */

public class PodrozRepository extends AsyncTaskLoader<List<Podroz>> {

    private static PodrozRepository INSTANCE;
    private Query query;
    private List<Podroz> cachedPodroze;
    private boolean checkCache;

    public static PodrozRepository getInstance(Context context) {
        if(INSTANCE==null) {
            INSTANCE = new PodrozRepository(context);
        }
        return INSTANCE;
    }

//    public PodrozRepository(Context context, Query query) {
//        super(context);
//        this.checkCache = true;
//        // ...
//        setQuery(query);
//        onContentChanged();
//    }

    public PodrozRepository(Context context) {
        super(context);
        this.checkCache = true;
        // ...
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
        return pobierzPodroze();
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

    public List<Podroz> pobierzPodroze() {
        List<Podroz> results = null;
        if(query!=null) {
            if(checkCache) {
                if(cachedPodroze!=null) {
                    return cachedPodroze;
                } else {
                    // ...
                }
            }

            if(results == null || results.isEmpty()) {
                results = Service.getInstance().pobierzPodroze(query.getSkad(), query.getDokad(), query.getMiesiac(), query.getRok());
//                saveLocalDataSourceMiasta(results);
            }
            processResults(results);
        }
        return results;
    }

    public Podroz getPodroz(int position) {
        List<Podroz> results = pobierzPodroze();
        if(results!=null) {
            return results.get(position);
        }
        return null;
    }

    private void processResults(List<Podroz> podroze) {
        if (podroze == null) {
            cachedPodroze = null;
            checkCache = true;
            return;
        }
        if (cachedPodroze == null) {
            cachedPodroze = new LinkedList<>();
        }
        cachedPodroze.clear();
        for (Podroz p : podroze) {
            cachedPodroze.add(p);
        }
        checkCache = true;
    }
}
