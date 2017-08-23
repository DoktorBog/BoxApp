package io.box.boxapp.network.repositorys;


import io.box.boxapp.models.BoxResponse;
import io.box.boxapp.models.LoginResponse;
import io.reactivex.Observable;

public interface Repository {
    Observable<BoxResponse> boxSubscribe(int boxId, String boxColorId);
    Observable<LoginResponse> login(String email, String password, boolean isSingUp);
}
