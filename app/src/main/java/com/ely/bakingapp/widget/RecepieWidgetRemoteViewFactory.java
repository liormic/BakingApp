package com.ely.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ely.bakingapp.Ingredients;
import com.ely.bakingapp.R;
import com.ely.bakingapp.displayRecepies.RecepieOptionsFragment;

import java.util.List;

/**
 * Created by Lior on 5/2/2018.
 */

public class RecepieWidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
     List<Ingredients> ingredientsList;

    public RecepieWidgetRemoteViewFactory(Context context, Intent intent) {
        this.context = context;
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        ingredientsList = RecepieOptionsFragment.ingredientsList;


        }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        try {
            return ingredientsList.size();
        }catch (Exception e){
           return 4;
        }

    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews rv  = new RemoteViews(context.getPackageName(), R.layout.recepie_widget_item);

        rv.setTextViewText(R.id.widget_recepie_item,ingredientsList.get(position).getIngredient());
        Bundle extras = new Bundle();
        extras.putLong(RecepieWidgetProvider.RECEPIE_ID, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.widget_recepie_item, fillInIntent);
        return rv;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
