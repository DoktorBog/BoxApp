package io.box.boxapp.network.repositorys;


import io.box.boxapp.models.BoxResponse;
import io.reactivex.Observable;

public interface Repository {
    Observable<BoxResponse> boxSubscribe(int boxId, int boxColorId);
}
