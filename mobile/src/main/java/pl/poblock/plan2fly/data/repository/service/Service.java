package pl.poblock.plan2fly.data.repository.service;

import android.util.Log;

import java.io.IOException;
import java.util.List;

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

    public Service() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IService.class);
    }

    public List<Podroz> pobierzPodroze(String skad, String dokad, int miesiac, int rok) {
        Call<List<Podroz>> call = service.podroze(skad, dokad, miesiac, rok);
        try {
            Response<List<Podroz>> response = call.execute();
            Log.d("PODROZ", response.toString());
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
            Log.d("MIASTO", response.toString());
            if(response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
