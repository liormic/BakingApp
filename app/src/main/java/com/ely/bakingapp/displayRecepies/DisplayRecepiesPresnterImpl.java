package com.ely.bakingapp.displayRecepies;

import com.ely.bakingapp.RecepieResults;
import com.ely.bakingapp.network.CallInterceptor;
import com.ely.bakingapp.network.Module;
import com.ely.bakingapp.network.RecepieClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Lior on 4/23/2018.
 */

public class DisplayRecepiesPresnterImpl implements DisaplyRecepiesPresenter{

    DisplayRecepiesView view;

    @Override
    public void setView(DisplayRecepiesView view){
       this.view = view;
    }

    @Override
    public void executeCall() {

        Call<RecepieResults> recepieResultsCall = setupRetrofitClient().getRecepieObjects();
        recepieResultsCall.enqueue(new Callback<RecepieResults>() {
            @Override
            public void onResponse(Call<RecepieResults> call, Response<RecepieResults> response) {

            }

            @Override
            public void onFailure(Call<RecepieResults> call, Throwable t) {

            }
        });

    }

    @Override
    public RecepieClient setupRetrofitClient(){
        OkHttpClient okHttpClient = Module.generateOkHttpClient(new CallInterceptor());
        Retrofit retrofit  = Module.createRetrofitInstance(okHttpClient);
        RecepieClient recepieClient = retrofit.create(RecepieClient.class);
        return recepieClient;
    }



}
