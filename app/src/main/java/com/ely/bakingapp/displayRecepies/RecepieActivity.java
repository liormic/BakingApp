package com.ely.bakingapp.displayRecepies;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.ely.bakingapp.idilingResouce.SimpleIdilingResource;

import java.util.ArrayList;

public class RecepieActivity extends AppCompatActivity implements DisplayRecepiesView {
    DisplayRecepiesPresnterImpl disaplyRecepiesPresenterImpl = new DisplayRecepiesPresnterImpl();
    public static ArrayList<RecepieObject> recepieObjects;
    public android.app.Fragment fragment;
    public boolean isTabletLayout;
    @Nullable
    private SimpleIdilingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isTablet(this)) {
            isTabletLayout = true;
        }

        disaplyRecepiesPresenterImpl.setView(this);

        if (getFragmentManager().getBackStackEntryCount() == 0) {
            disaplyRecepiesPresenterImpl.executeCall();
        }
        isTabletLayout = isTablet(this);
        if (isTabletLayout) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


        getIdlingResource();

    }

    @Override
    @VisibleForTesting
    @NonNull
    public SimpleIdilingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdilingResource();
        }
        return mIdlingResource;
    }

    public void initFragment(ArrayList<RecepieObject> recepieResults) {


        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList(getString(R.string.recepies), recepieResults);
        DisplayRecepiesFragment displayRecepiesFragment = new DisplayRecepiesFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        displayRecepiesFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, displayRecepiesFragment, getString(R.string.dr));
        fragmentTransaction.addToBackStack(getString(R.string.dr));
        fragmentTransaction.commitAllowingStateLoss();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
    }

    public void startFragment(Bundle bundle) {
        DisplayIngerdientsFragment displayIngerdientsFragment = new DisplayIngerdientsFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        displayIngerdientsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, displayIngerdientsFragment, getString(R.string.di));
        fragmentTransaction.addToBackStack(getString(R.string.di));
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void getRecepies(ArrayList<RecepieObject> recepieResults) {
        recepieObjects = recepieResults;
        initFragment(recepieObjects);
    }

    public static boolean isTablet(Context context) {
        return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }


}
