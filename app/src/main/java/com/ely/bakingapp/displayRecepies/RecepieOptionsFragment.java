package com.ely.bakingapp.displayRecepies;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ely.bakingapp.Ingredients;
import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.ely.bakingapp.adapters.RecepieOptionsAdapter;
import com.ely.bakingapp.widget.RecepieWidgetService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecepieOptionsFragment extends android.app.Fragment implements RecepieOptionsAdapter.ListItemClickListener {
    private ArrayList<RecepieObject> recepieObjects;
    public static List<Ingredients> ingredientsList;
    private int stepPosition;
    private boolean isTabletayout;
    public static final String RV_STATE_STRING_Options = "State_String";
    @BindView(R.id.rv_steps)
    RecyclerView recyclerView;
    @BindView(R.id.ingredients)
    TextView ingredientsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
        Bundle bundle = getArguments();
        stepPosition = bundle.getInt(getActivity().getString(R.string.step_position));
        recepieObjects = bundle.getParcelableArrayList(getActivity().getString(R.string.recepies));
        ingredientsList = recepieObjects.get(stepPosition).getIngredients();
        ArrayList ingredientsArrayList = new ArrayList<Ingredients>();
        ingredientsArrayList.addAll(ingredientsList);
        RecepieWidgetService.startRecepieWidgetService(getActivity(), ingredientsArrayList);
        RecepieOptionsAdapter recepieOptionsAdapter = new RecepieOptionsAdapter(recepieObjects, this, stepPosition);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(getActivity().getString(R.string.recepies), recepieObjects);
        outState.putInt(getActivity().getString(R.string.step_position), stepPosition);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            stepPosition = savedInstanceState.getInt(getActivity().getString(R.string.step_position));
            recepieObjects = savedInstanceState.getParcelableArrayList(getActivity().getString(R.string.recepies));
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            // initRecyclerView();
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(RV_STATE_STRING_Options);
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
            recepieObjects = savedInstanceState.getParcelableArrayList(getActivity().getString(R.string.recepies));
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recpie_options_fragment, container, false);
        ButterKnife.bind(this, rootView);
        isTabletayout = isTablet(getActivity());
        initRecyclerView();
        if (savedInstanceState != null) {
            recepieObjects = savedInstanceState.getParcelableArrayList(getActivity().getString(R.string.recepies));
        }
        ((RecepieActivity)getActivity()).setHomeButton(true);
        ingredientsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(getActivity().getString(R.string.recepies), recepieObjects);
                bundle.putInt(getActivity().getString(R.string.step_position), stepPosition);
                ((RecepieActivity) getActivity()).startFragment(bundle);
            }
        });
        return rootView;

    }


    private void initRecyclerView() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        RecepieOptionsAdapter recepieOptionsAdapter = new RecepieOptionsAdapter(recepieObjects, this, stepPosition);
        recyclerView.setAdapter(recepieOptionsAdapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {


        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(getActivity().getString(R.string.recepies), recepieObjects);
        bundle.putInt(getActivity().getString(R.string.step_position), stepPosition);
        bundle.putInt(getActivity().getString(R.string.clicked_step), clickedItemIndex);
        DisplayStepDetailsFragment displayStepDetailsFragment = new DisplayStepDetailsFragment();
        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        displayStepDetailsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, displayStepDetailsFragment, getString(R.string.df));
        fragmentTransaction.addToBackStack(getString(R.string.df));
        fragmentTransaction.commitAllowingStateLoss();


    }


    public static boolean isTablet(Context context) {
        return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }

}
