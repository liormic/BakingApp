package com.ely.bakingapp.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Module {

    private static Retrofit retrofit;
    private static final int TIMEOUTCONNECTINSEC = 100;

    private static final String BASE_URL_RECEPIE = "https://d17h27t6h515a5.cloudfront.net";



    public static Retrofit createRetrofitInstance(OkHttpClient okHttpClient) {
        if (retrofit == null) {

            return new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL_RECEPIE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return  retrofit;
    }

    public  static OkHttpClient generateOkHttpClient (CallInterceptor callInterceptor){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().connectTimeout(TIMEOUTCONNECTINSEC, TimeUnit.SECONDS)

                .addInterceptor(callInterceptor)
                .build();
    }

}
