package com.ely.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by lior on 4/23/18.
 */

public class RecepieAdapter extends RecyclerView.Adapter<RecepieAdapter.ViewHolder> {
    private ArrayList<RecepieObject> recepieObjects;
    private ListItemClickListener listItemClickListener;
    public RecepieAdapter(ArrayList<RecepieObject> recepieObjects, ListItemClickListener listItemClickListener) {
        this.recepieObjects = recepieObjects;
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recpie_ltem_layout, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recpieName.setText(recepieObjects.get(position).getName());
        holder.servingsNum.setText(recepieObjects.get(0).getServings());
    }


    @Override
    public int getItemCount() {
        return recepieObjects.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textVifew;
        @BindView(R.id.recepie_name) TextView recpieName;
        @BindView(R.id.servingsnum) TextView servingsNum;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.onListItemClick(clickedPosition);
        }
    }

}
