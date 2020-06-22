package com.digitalhouse.bnews.modules.signIn.viewmodel;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.digitalhouse.bnews.repository.FirebaseRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SignInViewModel extends AndroidViewModel {

    MutableLiveData<Boolean> signedInLiveData = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();
    FirebaseRepository repository = new FirebaseRepository();

    public SignInViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> getSignedInLiveData() {
        return signedInLiveData;
    }

    public void createsUser(String name, String email, String password, Activity activity) {
        disposable.add(
                repository.createsNewUser(name, email, password, activity)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe( () -> signedInLiveData.setValue(true),
                                throwable -> {
                                    throwable.printStackTrace();
                                    signedInLiveData.setValue(false);
                                })
        );
    }
}
