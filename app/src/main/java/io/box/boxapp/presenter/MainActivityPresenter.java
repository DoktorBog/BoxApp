package io.box.boxapp.presenter;

import java.util.UUID;

import io.box.boxapp.models.realm.User;
import io.box.boxapp.network.usecases.GetBoxUsecase;
import io.box.boxapp.network.usecases.LoginUsecase;
import io.box.boxapp.view.MainView;
import io.box.boxapp.view.SignView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


public class MainActivityPresenter implements Presenter<MainView> {

    private Disposable getBoxSubscription;
    private MainView mainView;
    private GetBoxUsecase getBoxUsecase;

    public MainActivityPresenter(GetBoxUsecase getBoxUsecase) {
        this.getBoxUsecase = getBoxUsecase;
    }

    @Override
    public void onCreate() {
        if(getBoxSubscription != null && !getBoxSubscription.isDisposed()) {
            getBoxSubscription.dispose();
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

    public void fetch(int boxId, String color) {
        getBoxSubscription = getBoxUsecase.fetch(boxId, color)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(boxResponse -> mainView.showContent(), e -> mainView.showError());
    }

    @Override
    public void attachView(MainView view) {
        mainView = view;
    }
}
