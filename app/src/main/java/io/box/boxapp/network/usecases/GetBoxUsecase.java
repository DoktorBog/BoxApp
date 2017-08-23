package io.box.boxapp.network.usecases;

import io.box.boxapp.models.BoxResponse;
import io.box.boxapp.network.repositorys.Repository;
import io.reactivex.Observable;

public class GetBoxUsecase implements Usecase<BoxResponse>  {

    private Repository repository;
    private int id;
    private int colorId;

    public GetBoxUsecase(Repository repository) {
        this.repository = repository;
    }

    public Observable<BoxResponse> fetch(int id, int clolorId) {
        this.id = id;
        this.colorId = colorId;
        return execute();
    }

    @Override
    public Observable<BoxResponse> execute() {
        return repository.boxSubscribe(id, colorId);
    }
}
