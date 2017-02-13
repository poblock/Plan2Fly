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

public class PodrozRepository {

    private static PodrozRepository INSTANCE;
    private Query query;
    private List<Podroz> cachedPodroze;
    private boolean checkCache;

    public static PodrozRepository getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new PodrozRepository();
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

    public PodrozRepository() {
        this.checkCache = true;
    }

    public PodrozRepository(Context context) {
//        super(context);
        this.checkCache = true;
        // ...
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Query getQuery() {
        return query;
    }

    public List<Podroz> pobierzPodroze() {
        return pobierzPodroze(false);
    }

    public List<Podroz> pobierzPodroze(boolean forceReload) {
        List<Podroz> results = null;
        if(query!=null) {
            if(checkCache && !forceReload) {
                if(cachedPodroze!=null) {
                    return cachedPodroze;
                } else {
                    // ...
                }
            }

            if(results == null || results.isEmpty()) {
                results = Service.getInstance().pobierzPodroze(query.getSkad(), query.getDokad(), query.getMiesiac(), query.getRok(), query.czyWDC());
//                saveLocalDataSourceMiasta(results);
            }
            processResults(results, query);
        }
        return results;
    }

    public Podroz getPodroz(int position) {
        if(position!=999) {
            List<Podroz> results = pobierzPodroze();
            if(results!=null) {
                return results.get(position);
            }
        } else {
            return Ulubione.preparePodroz();
        }
        return null;
    }

    private void processResults(List<Podroz> podroze, Query query) {
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
