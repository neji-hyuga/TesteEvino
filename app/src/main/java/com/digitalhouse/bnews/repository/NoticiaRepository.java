package com.digitalhouse.bnews.repository;

import com.digitalhouse.bnews.model.Noticia;
import com.digitalhouse.bnews.service.RetrofitService;

import java.util.List;

import io.reactivex.Observable;

public class NoticiaRepository {

    private RetrofitService retrofitService = new RetrofitService();
    private static final String API_KEY = "2af0470fc1404d868a96ee30adfeecbe";
    private static final String FORMAT = "json";
    private static final int PAGESIZE = 20;
    private static final String SORTBY = "relevancy";
    private static final String SOURCE = "google-news-br";
    private static final String LANGUAGE = "pt";

    public Observable<List<Noticia>> getNoticiaListApi(String search){
        return retrofitService.getNoticiaApi()
                .getNoticias(API_KEY, FORMAT, search, PAGESIZE, SORTBY, SOURCE, LANGUAGE)
                .map(noticiaResponse -> noticiaResponse.getArticles());
    }
}
