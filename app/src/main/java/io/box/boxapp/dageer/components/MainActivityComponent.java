package io.box.boxapp.dageer.components;

import dagger.Component;
import io.box.boxapp.dageer.modules.MainActivityModule;
import io.box.boxapp.dageer.modules.SignActivityModule;
import io.box.boxapp.dageer.scopes.MainActivityScope;
import io.box.boxapp.network.repositorys.RetrofitRestRepository;
import io.box.boxapp.presenter.MainActivityPresenter;
import io.box.boxapp.presenter.SignPresenter;
import io.box.boxapp.ui.MainActivity;
import io.box.boxapp.ui.SignActivity;

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
    RetrofitRestRepository retrofitRestRepository();
    MainActivityPresenter mainActivityPresenter();
}
