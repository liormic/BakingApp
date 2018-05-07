package com.ely.bakingapp.displayRecepies;

import android.support.annotation.Nullable;

import com.ely.bakingapp.RecepieObject;
import com.ely.bakingapp.idilingResouce.SimpleIdilingResource;
import com.ely.bakingapp.network.CallInterceptor;
import com.ely.bakingapp.network.Module;
import com.ely.bakingapp.network.RecepieClient;

import java.util.ArrayList;

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



    @Nullable
    SimpleIdilingResource simpleIdilingResource;


    @Override
    public void setView(DisplayRecepiesView view){
       this.view = view;
    }

    @Override
    public void executeCall() {
        simpleIdilingResource = (SimpleIdilingResource) view.getIdlingResource();
        if (simpleIdilingResource != null) {
            simpleIdilingResource.setIdleState(false);
        }
        Call<ArrayList<RecepieObject>> recepieResultsCall = setupRetrofitClient().getRecepies();
        recepieResultsCall.enqueue(new Callback<ArrayList<RecepieObject>>() {
            @Override
            public void onResponse(Call<ArrayList<RecepieObject>> call, Response<ArrayList<RecepieObject>> response) {
                ArrayList<RecepieObject> RecepieObject  = response.body();

                simpleIdilingResource.setIdleState(true);
                view.getRecepies(RecepieObject);

            }

            @Override
            public void onFailure(Call<ArrayList<RecepieObject>> call, Throwable t) {
               t.printStackTrace();
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
