package com.ely.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.ely.bakingapp.Ingredients;
import com.ely.bakingapp.R;
import com.ely.bakingapp.displayRecepies.RecepieActivity;

import java.util.ArrayList;

import static com.ely.bakingapp.widget.RecepieWidgetService.GET_INGRIDENTS;

/**
 * Implementation of App Widget functionality.
 */
public class RecepieWidgetProvider extends AppWidgetProvider {
    public static final String OPEN_INGRIDENTS = "OPEN_INGRIDENTS";
    public static final String RECEPIE_ID = "RECEPIE_ID";


    static ArrayList<Ingredients> ingredientsArrayList;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.recepie_widget);
        Intent intent = new Intent(context, RecepieRemoteViewWidget.class);
        Intent launchApp = new Intent(context, RecepieActivity.class);
        launchApp.addCategory(Intent.ACTION_MAIN);
        launchApp.addCategory(Intent.CATEGORY_LAUNCHER);
        launchApp.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchApp, 0);
        rv.setPendingIntentTemplate(R.id.widgetListView, pendingIntent);

        rv.setRemoteAdapter(R.id.widgetListView, intent);

        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    }

    public static void updateBakingWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecepieWidgetProvider.class));

        final String receivedAction = intent.getAction();

        if (receivedAction.equals("android.appwidget.action.APPWIDGET_UPDATE")) {
            ingredientsArrayList = intent.getParcelableArrayListExtra(GET_INGRIDENTS);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widgetListView);
            RecepieWidgetProvider.updateBakingWidgets(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);
        }


    }


    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

