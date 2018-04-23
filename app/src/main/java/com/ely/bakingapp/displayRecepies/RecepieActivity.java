package com.ely.bakingapp.displayRecepies;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.ely.bakingapp.RecepieResults;

import java.util.ArrayList;

public class RecepieActivity extends AppCompatActivity implements DisplayRecepiesView {
    DisaplyRecepiesPresenter disaplyRecepiesPresenter;
    private ArrayList<RecepieObject> recepieObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disaplyRecepiesPresenter.setView(this);
        initFragment();

    }

    public void initFragment() {
        Bundle bundle = new Bundle();
        disaplyRecepiesPresenter.executeCall();
        if (recepieObjects != null) {
            bundle.putParcelableArrayList(getString(R.string.recepies), recepieObjects);
            DisplayRecepiesFragment displayRecepiesFragment = new DisplayRecepiesFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            displayRecepiesFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container, displayRecepiesFragment, "Display Recepies");
            fragmentTransaction.addToBackStack("Display Recepies");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void getRecepies(RecepieResults recepieResults) {
        recepieObjects = recepieResults.getRecepieObjects();
    }

}
