package pl.poblock.plan2fly.data.repository;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import pl.poblock.plan2fly.data.model.Lot;
import pl.poblock.plan2fly.data.model.Miasto;
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
    private Context context;

    public static PodrozRepository getInstance(Context context) {
        if(INSTANCE==null) {
            INSTANCE = new PodrozRepository(context);
        }
        return INSTANCE;
    }

    public PodrozRepository() {
        this.checkCache = true;
    }

    public PodrozRepository(Context context) {
        this.context = context;
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
                results = Service.getInstance().pobierzPodroze(query.getSkad(), query.getDokad(), query.getMiesiac(), query.getRok(), query.czyWDC(), query.getOfert());
//                saveLocalDataSourceMiasta(results);
            }
            processResults(results);
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

        Double minCenaWylot = Double.MAX_VALUE;
        int wylotIdx = -1;
        Double minCenaPowrot = Double.MAX_VALUE;
        int powrotIdx = -1;
        for (int i=0; i<podroze.size(); i++) {
            Podroz p = podroze.get(i);
            writeCityName(p.getPodrozTam().getLoty());
            writeCityName(p.getPodrozPowrot().getLoty());
            if(p.getPodrozTam().getSuma() <= minCenaWylot) {
                minCenaWylot = p.getPodrozTam().getSuma();
                wylotIdx = i;
            }
            if(p.getPodrozPowrot().getSuma() < minCenaPowrot) {
                minCenaPowrot = p.getPodrozPowrot().getSuma();
                powrotIdx = i;
            }
        }

        for (int i=0; i<podroze.size(); i++) {
            Podroz p = podroze.get(i);
            if((p.getPodrozTam().getSuma() == minCenaWylot) || i == wylotIdx) {
                Log.i("TRIP", "Najtanszy wylot "+i);
                p.setNajtanszyWylot(true);
            }
            if((p.getPodrozPowrot().getSuma() == minCenaPowrot) || i == powrotIdx) {
                Log.i("TRIP", "Najtanszy powrot "+i);
                p.setNajtanszyPowrot(true);
            }
            cachedPodroze.add(p);
        }
        Log.i("TRIP","Podroze : "+cachedPodroze);
        checkCache = true;
    }

    private void writeCityName(List<Lot> loty) {
        List<Miasto> lista = MiastoRepository.getInstance(context).pobierzMiasta();
        for(Lot l : loty) {
            for(Miasto m : lista) {
                if(m.getCode().equals(l.getSkad())) {
                    l.setSkadFull(m.getName());
                } else if(m.getCode().equals(l.getDokad())) {
                    l.setDokadFull(m.getName());
                }

                if(l.getSkadFull()!=null && l.getDokadFull()!=null) {
                    continue;
                }
            }
        }
    }
}
