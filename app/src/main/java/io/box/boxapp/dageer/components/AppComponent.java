package io.box.boxapp.dageer.components;


import dagger.Component;
import io.box.boxapp.BoxAppApplication;
import io.box.boxapp.dageer.modules.AppModule;
import io.box.boxapp.dageer.modules.NetworkModule;
import io.box.boxapp.dageer.scopes.AppScope;
import io.box.boxapp.models.BoxResponse;
import io.box.boxapp.network.repositorys.RetrofitRestRepository;

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(BoxAppApplication application);
    RetrofitRestRepository retrofitRestRepository();

}
