package com.ely.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;

import java.util.ArrayList;

/**
 * Created by Lior on 5/2/2018.
 */

public class RecepieWidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private ArrayList<RecepieObject> recepieObjects;

    public RecepieWidgetRemoteViewFactory(Context context, Intent intent) {
        this.context = context;
         recepieObjects = RecepieWidgetProvider.getRecepieObjects();

    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if(RecepieWidgetProvider.getRecepieObjects()!=null) {
            int size = RecepieWidgetProvider.getRecepieObjects().size();
            return size;
        }else
            return 4;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv  = new RemoteViews(context.getPackageName(), R.layout.recepie_widget_item);
        rv.setTextViewText(R.id.widget_recepie_item,recepieObjects.get(position).getName());
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
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
