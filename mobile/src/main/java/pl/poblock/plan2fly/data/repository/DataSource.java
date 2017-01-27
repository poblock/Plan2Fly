package pl.poblock.plan2fly.data.repository;

import java.util.List;

import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.model.Podroz;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public interface DataSource {
    List<Podroz> pobierzPodroze(Query query);
    List<Miasto> pobierzMiasta();
}
