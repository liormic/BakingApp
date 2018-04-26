package com.ely.bakingapp.displayRecepies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.ely.bakingapp.adapters.RecepieOptionsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;



public class RecepieOptionsFragment extends android.app.Fragment implements RecepieOptionsAdapter.ListItemClickListener {
    private ArrayList<RecepieObject> recepieObjects;
    private int stepPosition;
    @BindView(R.id.rv_steps)RecyclerView recyclerView;
    @BindView(R.id.ingredients)TextView ingredientsView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        stepPosition = bundle.getInt("step_position");
        recepieObjects = bundle.getParcelableArrayList(getActivity().getString(R.string.recepies));
        RecepieOptionsAdapter recepieOptionsAdapter = new RecepieOptionsAdapter(recepieObjects, this,stepPosition);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recpie_options_fragment,container,false);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        ingredientsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(getActivity().getString(R.string.recepies),recepieObjects);
                bundle.putInt("step_position",stepPosition);
                ((RecepieActivity)getActivity()).startFragment(bundle);
            }
        });
        return rootView;

    }


    private void initRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        RecepieOptionsAdapter recepieOptionsAdapter = new RecepieOptionsAdapter(recepieObjects, this,stepPosition);
        recyclerView.setAdapter(recepieOptionsAdapter);

    }



    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(),DisplayStepDetailsActivity.class);
        getActivity().startActivity(intent);
    }


}
