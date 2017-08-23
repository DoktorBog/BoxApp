package io.box.boxapp.dageer.components;

import dagger.Component;
import io.box.boxapp.dageer.modules.SignActivityModule;
import io.box.boxapp.dageer.scopes.SignActivityScope;
import io.box.boxapp.network.repositorys.RetrofitRestRepository;
import io.box.boxapp.presenter.SignPresenter;
import io.box.boxapp.ui.SignActivity;

@SignActivityScope
@Component(modules = SignActivityModule.class, dependencies = AppComponent.class)
public interface SignActivityComponent {
    void inject(SignActivity activity);
    RetrofitRestRepository retrofitRestRepository();
    SignPresenter signPresenter();
}
