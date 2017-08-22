package io.box.boxapp.network.repositorys;


import io.box.boxapp.models.BoxResponse;
import io.box.boxapp.network.ApiService;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitRestRepository implements Repository {

    private ApiService apiService;

    public RetrofitRestRepository(Retrofit retrofit) {
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<BoxResponse> boxSubscribe(int boxId, int boxColorId) {
        return apiService.boxSubscribe(boxId, boxColorId);
    }
}