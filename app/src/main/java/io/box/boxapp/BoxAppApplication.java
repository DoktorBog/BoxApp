package io.box.boxapp;

import android.app.Application;

import io.box.boxapp.dageer.components.AppComponent;
import io.box.boxapp.dageer.components.DaggerAppComponent;
import io.box.boxapp.models.realm.User;
import io.realm.Realm;
import io.realm.RealmResults;
import lombok.Getter;

public class BoxAppApplication extends Application {

    @Getter
    private AppComponent appComponent;

    private static BoxAppApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Realm.init(this);

        appComponent = DaggerAppComponent.builder()
                .build();
        appComponent.inject(this);
    }

    public static synchronized BoxAppApplication getInstance() {
        return mInstance;
    }

    public static void logout(){
        Realm.getDefaultInstance().executeTransaction(realm -> {
            RealmResults<User> users = realm.where(User.class).findAll();
            for (User user : users) user.setInApp(false);
        });
    }
}
