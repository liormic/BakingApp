package com.ely.bakingapp.network;


import com.ely.bakingapp.RecepieObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;



public interface RecepieClient {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<RecepieObject>> getRecepies();
}

