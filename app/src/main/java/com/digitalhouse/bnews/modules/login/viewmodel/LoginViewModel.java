package com.digitalhouse.bnews.modules.login.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.digitalhouse.bnews.repository.FirebaseRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends AndroidViewModel {

    MutableLiveData<Boolean> loaderLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loggedInLiveData = new MutableLiveData<>();

    CompositeDisposable disposable = new CompositeDisposable();

    FirebaseRepository repository = new FirebaseRepository();

    public MutableLiveData<Boolean> getLoaderLiveData() {
        return loaderLiveData;
    }

    public MutableLiveData<Boolean> getLoggedInLiveData() {
        return loggedInLiveData;
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void authenticateUser(String email, String password) {
        loaderLiveData.setValue(true);
        disposable.add(
                repository.authenticate(email, password)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .doOnTerminate(() -> loaderLiveData.setValue(false))
                        .subscribe(() -> loggedInLiveData.setValue(true),
                                throwable -> loggedInLiveData.setValue(false))
        );
    }

}
