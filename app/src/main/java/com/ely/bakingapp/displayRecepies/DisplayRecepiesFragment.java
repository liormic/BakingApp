package com.ely.bakingapp.displayRecepies;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.ely.bakingapp.adapters.RecepieAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayRecepiesFragment extends android.app.Fragment implements View.OnClickListener,RecepieAdapter.ListItemClickListener {
    private ArrayList<RecepieObject> recepieObjects;
    @BindView(R.id.recepie_rv)RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        recepieObjects = bundle.getParcelableArrayList(getActivity().getString(R.string.recepies));
        RecepieAdapter recepieAdapter = new RecepieAdapter(recepieObjects, this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recepie_fragment,container,false);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void initRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecepieAdapter recepieAdapter = new RecepieAdapter(recepieObjects, this);
        recyclerView.setAdapter(recepieAdapter);

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(getActivity().getString(R.string.recepies),recepieObjects);
        bundle.putInt("step_position",clickedItemIndex);

        RecepieOptionsFragment recepieOptionsFragment= new RecepieOptionsFragment();
        FragmentTransaction fragmentTransaction =getActivity().getFragmentManager().beginTransaction();
        recepieOptionsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, recepieOptionsFragment, "Display Steps");
        fragmentTransaction.addToBackStack("Display Steps");
        fragmentTransaction.commitAllowingStateLoss();
    }
    @Override
    public void onClick(View v) {

    }
}
