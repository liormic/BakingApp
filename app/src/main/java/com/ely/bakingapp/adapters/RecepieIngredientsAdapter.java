package com.ely.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;

import java.util.ArrayList;

/**
 * Created by Lior on 4/25/2018.
 */

public class RecepieIngredientsAdapter extends RecyclerView.Adapter<RecepieIngredientsAdapter.ViewHolder> {
    private ArrayList<RecepieObject> recepieObjects;
    private int stepPosition;

    public RecepieIngredientsAdapter(ArrayList<RecepieObject> recepieObjects,  int stepPosition) {

        this.recepieObjects = recepieObjects;
        this.stepPosition = stepPosition;
    }

    @Override
    public RecepieIngredientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recepie_ingredient_item_layout,parent,false);
        return new RecepieIngredientsAdapter.ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(RecepieIngredientsAdapter.ViewHolder holder, int position) {
        holder.ingredientView.setText(recepieObjects.get(stepPosition).getIngredients().get(position).getIngredient());
        holder.mesuareView.setText(recepieObjects.get(stepPosition).getIngredients().get(position).getMeasure());
        holder.quantityView.setText(recepieObjects.get(stepPosition).getIngredients().get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return recepieObjects.get(stepPosition).getIngredients().size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientView;
        TextView mesuareView;
        TextView quantityView;

        public ViewHolder(View itemView) {
            super(itemView);
            ingredientView = itemView.findViewById(R.id.ingredient_name);
            mesuareView = itemView.findViewById(R.id.measure);
            quantityView = itemView.findViewById(R.id.quantity);
        }


    }
}
