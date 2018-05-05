package com.ely.bakingapp.displayRecepies;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.ely.bakingapp.widget.RecepieWidgetProvider;
import com.ely.bakingapp.widget.RecepieWidgetService;

import java.util.ArrayList;

public class RecepieActivity extends AppCompatActivity implements DisplayRecepiesView {
    DisplayRecepiesPresnterImpl disaplyRecepiesPresenterImpl = new DisplayRecepiesPresnterImpl();
    private ArrayList<RecepieObject> recepieObjects;
    public android.app.Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disaplyRecepiesPresenterImpl.setView(this);

        if (getFragmentManager().getBackStackEntryCount()==0 ) {
            disaplyRecepiesPresenterImpl.executeCall();
        }
    }


    public void initFragment(ArrayList<RecepieObject> recepieResults) {
        Bundle bundle = new Bundle();

        if (recepieResults != null) {
            bundle.putParcelableArrayList(getString(R.string.recepies), recepieResults);
            DisplayRecepiesFragment displayRecepiesFragment = new DisplayRecepiesFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            displayRecepiesFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container, displayRecepiesFragment, "Display Recepies");
            fragmentTransaction.addToBackStack("Display Recepies");
            fragmentTransaction.commitAllowingStateLoss();

        }
    }

    public void startFragment(Bundle bundle){
        DisplayIngerdientsFragment displayIngerdientsFragment= new DisplayIngerdientsFragment();
        FragmentTransaction fragmentTransaction =getFragmentManager().beginTransaction();
        displayIngerdientsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, displayIngerdientsFragment, "Display Ingredients");
        fragmentTransaction.addToBackStack("Display Ingredients");
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void getRecepies( ArrayList<RecepieObject> recepieResults) {
        recepieObjects = recepieResults;
        RecepieWidgetService.startRecepieWidgetService(this,recepieObjects);
        initFragment(recepieObjects);
    }

}
