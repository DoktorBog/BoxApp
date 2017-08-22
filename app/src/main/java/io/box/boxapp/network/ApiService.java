package io.box.boxapp.network;


import io.box.boxapp.models.BoxResponse;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {

    @GET("subscribe")
    Observable<BoxResponse> boxSubscribe(@Query("id") int boxId, @Query("color") int boxColorId);
}
