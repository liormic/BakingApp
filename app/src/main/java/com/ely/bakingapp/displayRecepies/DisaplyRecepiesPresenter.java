package com.ely.bakingapp.displayRecepies;

import com.ely.bakingapp.network.RecepieClient;

/**
 * Created by Lior on 4/23/2018.
 */

public interface DisaplyRecepiesPresenter {
    RecepieClient setupRetrofitClient();
    void executeCall();
    void setView(DisplayRecepiesView view);
}
