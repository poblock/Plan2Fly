package pl.poblock.plan2fly.data.repository.service;

import java.util.List;

import pl.poblock.plan2fly.data.model.Linia;
import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.model.Polaczenie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public interface IService {
    @GET("api/v1/podroz/znajdz")
    Call<List<Podroz>> podroze(@Query("skad") String skad, @Query("dokad") String dokad, @Query("miesiac") int miesiac, @Query("rok") int rok, @Query("wdc") int czyWDC, @Query("top") int ofert);
    @GET("api/v1/polaczenia/miasta")
    Call<List<Miasto>> miasta();
    @GET("api/v1/polaczenia")
    Call<List<Linia>> polaczenia();
}
