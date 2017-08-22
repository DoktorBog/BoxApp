package io.box.boxapp.dageer.modules;

import dagger.Module;
import dagger.Provides;
import io.box.boxapp.dageer.scopes.AppScope;
import io.box.boxapp.network.ApiService;
import io.box.boxapp.network.repositorys.RetrofitRestRepository;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @AppScope
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @AppScope
    public Retrofit provideRetrofit(OkHttpClient client){
        String BASE_URL = "http://box.io/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @AppScope
    public RetrofitRestRepository provideRetrofitRestRepository(Retrofit retrofit){
        return new RetrofitRestRepository(retrofit);
    }

}
