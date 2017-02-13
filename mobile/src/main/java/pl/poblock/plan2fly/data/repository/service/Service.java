package pl.poblock.plan2fly.data.repository.service;

import android.util.Log;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import pl.poblock.plan2fly.data.model.Linia;
import pl.poblock.plan2fly.data.model.Miasto;
import pl.poblock.plan2fly.data.model.Podroz;
import pl.poblock.plan2fly.data.repository.service.IService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class Service {
    public static final String API_URL = "http://popis-poblock.rhcloud.com";
    private IService service;

    private static Service INSTANCE = null;

    public static Service getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new Service();
        }
        return INSTANCE;
    }

    private Service() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(IService.class);
    }

    public List<Podroz> pobierzPodroze(String skad, String dokad, int miesiac, int rok, int czyWDC) {
        Call<List<Podroz>> call = service.podroze(skad, dokad, miesiac, rok, czyWDC);
        try {
            Response<List<Podroz>> response = call.execute();
            if(response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Miasto> pobierzMiasta() {
        Call<List<Miasto>> call = service.miasta();
        try {
            Response<List<Miasto>> response = call.execute();
            if(response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Linia> pobierzDostepnePolaczenia() {
        Call<List<Linia>> call = service.polaczenia();
        try {
            Response<List<Linia>> response = call.execute();
            if(response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
