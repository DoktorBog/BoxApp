package io.box.boxapp.presenter;

import java.util.UUID;

import io.box.boxapp.models.realm.User;
import io.box.boxapp.network.usecases.LoginUsecase;
import io.box.boxapp.view.SignView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class SignPresenter implements Presenter<SignView> {

    private Disposable getloginSubscription;
    private SignView programsView;
    private LoginUsecase loginUsecase;

    public SignPresenter(LoginUsecase loginUsecase) {
        this.loginUsecase = loginUsecase;
    }

    @Override
    public void onCreate() {
        if(getloginSubscription != null && !getloginSubscription.isDisposed()) {
            getloginSubscription.dispose();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    public void fetch(String email, String password, boolean isSignUp) {
        getloginSubscription = loginUsecase.fetch(email, password, isSignUp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> programsView.showContent(), e -> programsView.showError());
    }

    public void imitateSignAction(String email, String password, boolean isSignUp){
        Realm.getDefaultInstance().executeTransaction(realm -> {
            if(isSignUp) {
                User user = realm.createObject(User.class, UUID.randomUUID().toString());
                user.setEmail(email);
                user.setPassword(password);
                user.setInApp(true);
                programsView.showContent();
            } else {
                User user = realm.where(User.class).equalTo("email", email).equalTo("password", password).findFirst();
                if(user == null) programsView.showError();
                else {
                    user.setInApp(true);
                    programsView.showContent();
                }
            }
        });
    }

    @Override
    public void attachView(SignView view) {
        programsView = view;
    }
}
