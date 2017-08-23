package io.box.boxapp.network.usecases;

import io.box.boxapp.models.BoxResponse;
import io.box.boxapp.models.LoginResponse;
import io.box.boxapp.network.repositorys.Repository;
import io.reactivex.Observable;

public class LoginUsecase implements Usecase<LoginResponse> {

    private Repository repository;
    private String email;
    private String password;
    private boolean isSignUp;

    public LoginUsecase(Repository repository) {
        this.repository = repository;
    }

    public Observable<LoginResponse> fetch(String email, String password, boolean isSignUp) {
        this.email = email;
        this.password = password;
        this.isSignUp = isSignUp;
        return execute();
    }

    @Override
    public Observable<LoginResponse> execute() {
        return repository.login(email, password, isSignUp);
    }
}
