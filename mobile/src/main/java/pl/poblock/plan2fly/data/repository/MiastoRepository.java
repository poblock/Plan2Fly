package pl.poblock.plan2fly.data.repository;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import java.util.LinkedList;
import java.util.List;

import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.repository.db.MiastaLocalSource;
import pl.poblock.plan2fly.data.repository.service.Service;

/**
 * Created by krzysztof.poblocki on 2017-01-31.
 */

public class MiastoRepository extends AsyncTaskLoader<List<Miasto>> {

    private MiastaLocalSource localService;
    private List<Miasto> cachedMiasta;
    private boolean checkCache;

    public MiastoRepository(Context context) {
        super(context);
        this.checkCache = true;
        this.localService = MiastaLocalSource.getInstance(context);
        onContentChanged();
    }

    @Override
    public List<Miasto> loadInBackground() {
        return pobierzMiasta();
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

    public List<Miasto> pobierzMiasta() {
        List<Miasto> results = null;

        if(checkCache) {
            if(cachedMiasta!=null) {
                return cachedMiasta;
            } else {
                results = localService.getMiasta();
            }
        }

        if(results == null || results.isEmpty()) {
            results = Service.getInstance().pobierzMiasta();
            saveLocalDataSourceMiasta(results);
        }

        processResults(results);

        return results;
    }

    private void processResults(List<Miasto> miasta) {
        if (miasta == null) {
            cachedMiasta = null;
            checkCache = true;
            return;
        }
        if (cachedMiasta == null) {
            cachedMiasta = new LinkedList<>();
        }
        cachedMiasta.clear();
        for (Miasto m : miasta) {
            cachedMiasta.add(m);
        }
        checkCache = true;
    }

    private void saveLocalDataSourceMiasta(List<Miasto> miasta) {
        if(miasta!=null) {
            for(Miasto m : miasta) {
                localService.saveMiasto(m);
            }
        }
    }
}
