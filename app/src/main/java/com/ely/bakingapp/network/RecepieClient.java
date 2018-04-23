package com.ely.bakingapp.network;


import com.ely.bakingapp.RecepieResults;

import retrofit2.Call;
import retrofit2.http.GET;



public interface RecepieClient {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<RecepieResults> getRecepieObjects();
}

