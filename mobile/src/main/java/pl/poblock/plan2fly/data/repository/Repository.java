package pl.poblock.plan2fly.data.repository;

import java.util.List;

import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.service.Service;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class Repository implements DataSource {
    private static Repository INSTANCE = null;
    private Service remoteService;
    private List<Podroz> cachedResults; // TODO
    private List<Miasto> cachedMiasta;

    public static Repository getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }
    private Repository() {
        remoteService = new Service();
    }
    @Override
    public List<Podroz> pobierzPodroze(Query query) {
        return remoteService.pobierzPodroze(query.getSkad(), query.getDokad(), query.getMiesiac(), query.getRok());
    }

    @Override
    public List<Miasto> pobierzMiasta() {
        return remoteService.pobierzMiasta();
    }

    public interface RepositoryObserver {
        void onPodrozChanged();
    }

    public List<Podroz> getCachedResults() {
        return cachedResults;
    }

    public void setCachedResults(List<Podroz> cachedResults) {
        this.cachedResults = cachedResults;
    }

    public List<Miasto> getCachedMiasta() {
        return cachedMiasta;
    }

    public void setCachedMiasta(List<Miasto> cachedMiasta) {
        this.cachedMiasta = cachedMiasta;
    }

    public Podroz getCachedResult(int position) {
        List<Podroz> results = getCachedResults();
        if(results!=null) {
            return results.get(position);
        }
        return null;
    }
}
