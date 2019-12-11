package com.digitalhouse.bnews.service;

import com.digitalhouse.bnews.service.api.NoticiaApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    private static final String BASE_URL = "https://newsapi.org/v2/";

    private Retrofit getRetrofit(){
        if (retrofit == null){
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
            clientBuilder.readTimeout(30,TimeUnit.SECONDS);
            clientBuilder.writeTimeout(30,TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(clientBuilder.build())
                    .build();
        }
        return retrofit;
    }

    public NoticiaApi getNoticiaApi (){
        return getRetrofit().create(NoticiaApi.class);
    }

}
