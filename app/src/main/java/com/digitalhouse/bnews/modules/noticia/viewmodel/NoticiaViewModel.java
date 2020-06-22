package com.digitalhouse.bnews.modules.noticia.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.digitalhouse.bnews.model.Noticia;
import com.digitalhouse.bnews.repository.NoticiaRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NoticiaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Noticia>> noticiaLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private NoticiaRepository noticiaRepository = new NoticiaRepository();

    public NoticiaViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Noticia>> getNoticiaLiveData() {
        return noticiaLiveData;
    }

    public void atualizarNoticias(String search){

        //String search = noticiaList.get(0).getCountry();
        disposable.add(
                noticiaRepository.getNoticiaListApi(search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(noticias -> noticiaLiveData.setValue(noticias),
                        throwable -> throwable.printStackTrace())

        );

    }




}
