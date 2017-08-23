package io.box.boxapp.presenter;

public interface Presenter<T> {
    void onCreate();
    void onStart();
    void onStop();
    void onPause();
    void attachView(T view);
}
