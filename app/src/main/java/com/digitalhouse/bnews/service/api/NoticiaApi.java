package com.digitalhouse.bnews.service.api;

import com.digitalhouse.bnews.model.NoticiaResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoticiaApi {

    @GET("everything")
    Observable<NoticiaResponse> getNoticias (@Query("apiKey") String apiKey,
                                             @Query("format") String format,
                                             @Query("q")String search,
                                             @Query("pageSize") int pageSize,
                                             @Query("sortBy") String sortBy,
                                             @Query("source") String source,
                                             @Query("language") String language);


}