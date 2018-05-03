package com.ely.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class RecepieWidgetProvider extends AppWidgetProvider {
    public static ArrayList<RecepieObject> getRecepieObjects() {
        return recepieObjects;
    }

    private static ArrayList<RecepieObject> recepieObjects;

    public static void setRecepieObjects(ArrayList<RecepieObject> receivedRecepieObjects){
        recepieObjects= receivedRecepieObjects;
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.recepie_widget);
            Intent intent = new Intent(context,RecepieRemoteViewWidget.class);
            intent.putParcelableArrayListExtra("recepie_objects",recepieObjects);
            views.setRemoteAdapter(R.id.widgetListView,intent);
            appWidgetManager.updateAppWidget(appWidgetId,views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

