package com.ely.bakingapp.displayRecepies;

import android.support.test.espresso.IdlingResource;

import com.ely.bakingapp.RecepieObject;

import java.util.ArrayList;

/**
 * Created by Lior on 4/23/2018.
 */

public interface DisplayRecepiesView {
    void getRecepies( ArrayList<RecepieObject> recepieResults);
    IdlingResource getIdlingResource();
}
