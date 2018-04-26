package com.ely.bakingapp.displayRecepies;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.ely.bakingapp.adapters.RecepieIngredientsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lior on 4/25/2018.
 */

public class DisplayIngerdientsFragment extends Fragment {
    private ArrayList<RecepieObject> recepieObjects;
    private int stepPosition;
    @BindView(R.id.rv_ingredients)RecyclerView recyclerView;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getActivity().getString(R.string.recepies),recepieObjects);
        outState.putInt("step_position",stepPosition);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null){
            stepPosition = savedInstanceState.getInt("step_position");
            recepieObjects = savedInstanceState.getParcelableArrayList(getActivity().getString(R.string.recepies));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null) {
            Bundle bundle = getArguments();
            stepPosition = bundle.getInt("step_position");
            recepieObjects = bundle.getParcelableArrayList(getActivity().getString(R.string.recepies));
            RecepieIngredientsAdapter recepieIngredientsAdapter = new RecepieIngredientsAdapter(recepieObjects, stepPosition);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recepie_fragment_ingredients,container,false);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;

    }


    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        RecepieIngredientsAdapter recepieIngredientsAdapter = new RecepieIngredientsAdapter(recepieObjects, stepPosition);
        recyclerView.setAdapter(recepieIngredientsAdapter);

    }



}



