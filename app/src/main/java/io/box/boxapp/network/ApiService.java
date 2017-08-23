package io.box.boxapp.network;


import io.box.boxapp.models.BoxResponse;
import io.box.boxapp.models.LoginResponse;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {

    @POST("subscribe")
    Observable<LoginResponse> login(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("isSingUp") boolean isSingUp);

    @GET("subscribe")
    Observable<BoxResponse> boxSubscribe(@Query("id") int boxId, @Query("color") String boxColorId);
}
