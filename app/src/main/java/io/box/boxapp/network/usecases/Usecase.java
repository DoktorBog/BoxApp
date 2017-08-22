package io.box.boxapp.network.usecases;

import io.reactivex.Observable;

interface Usecase<T> {
    Observable<T> execute();
}
