package io.box.boxapp.dageer.modules;

import dagger.Module;
import dagger.Provides;
import io.box.boxapp.dageer.scopes.MainActivityScope;
import io.box.boxapp.dageer.scopes.SignActivityScope;
import io.box.boxapp.network.repositorys.RetrofitRestRepository;
import io.box.boxapp.network.usecases.GetBoxUsecase;
import io.box.boxapp.network.usecases.LoginUsecase;
import io.box.boxapp.presenter.MainActivityPresenter;
import io.box.boxapp.presenter.SignPresenter;

@Module
public class MainActivityModule {

    @Provides
    @MainActivityScope
    public GetBoxUsecase provideLoginUsecase(RetrofitRestRepository repository){
        return new GetBoxUsecase(repository);
    }

    @Provides
    @MainActivityScope
    public MainActivityPresenter provideSignPresenter(GetBoxUsecase getBoxUsecase){
        return new MainActivityPresenter(getBoxUsecase);
    }
}
