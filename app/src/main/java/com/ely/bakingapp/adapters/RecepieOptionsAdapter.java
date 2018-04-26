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

public class RecepieOptionsAdapter extends RecyclerView.Adapter<RecepieOptionsAdapter.ViewHolder>{
    private RecepieOptionsAdapter.ListItemClickListener listItemClickListener;
    private ArrayList<RecepieObject> recepieObjects;
    private int stepPosition;

    public RecepieOptionsAdapter(ArrayList<RecepieObject> recepieObjects, ListItemClickListener listItemClickListener, int stepPosition) {
        this.listItemClickListener = listItemClickListener;
        this.recepieObjects = recepieObjects;
        this.stepPosition = stepPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recepie_step_item_layout,parent,false);
        return new ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.stepName.setText(recepieObjects.get(stepPosition).getSteps().get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return recepieObjects.get(stepPosition).getSteps().size();
    }


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView stepName;

        public ViewHolder(View itemView) {
            super(itemView);
            stepName = itemView.findViewById(R.id.step_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.onListItemClick(clickedPosition);
        }
    }
}
