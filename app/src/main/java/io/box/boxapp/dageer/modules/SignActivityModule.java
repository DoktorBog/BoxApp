package io.box.boxapp.dageer.modules;

import dagger.Module;
import dagger.Provides;
import io.box.boxapp.dageer.scopes.SignActivityScope;
import io.box.boxapp.network.repositorys.Repository;
import io.box.boxapp.network.repositorys.RetrofitRestRepository;
import io.box.boxapp.network.usecases.LoginUsecase;
import io.box.boxapp.presenter.SignPresenter;

@Module
public class SignActivityModule {

    @Provides
    @SignActivityScope
    public LoginUsecase provideLoginUsecase(RetrofitRestRepository repository){
        return new LoginUsecase(repository);
    }

    @Provides
    @SignActivityScope
    public SignPresenter provideSignPresenter(LoginUsecase loginUsecase){
        return new SignPresenter(loginUsecase);
    }
}
