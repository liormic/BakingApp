package com.ely.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.ely.bakingapp.Ingredients;

import java.util.ArrayList;

/**
 * Created by lior on 5/4/18.
 */

public class  RecepieWidgetService extends IntentService {
    public static final String GET_INGRIDENTS = "GET_INGRIDENTS";
    public static final String UPDATE_WIDGET = "UPDATE_WIDGET";

    public RecepieWidgetService() {
        super("RecepieWidgetService");
    }




    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<Ingredients> ingredientsArrayList = intent.getParcelableArrayListExtra(GET_INGRIDENTS);

            handleActionWidgetService(ingredientsArrayList);

        }
    }

    public static void startRecepieWidgetService(Context context, ArrayList<Ingredients> ingredientsList) {
        Intent intent = new Intent(context, RecepieWidgetService.class);
        intent.putParcelableArrayListExtra(GET_INGRIDENTS,ingredientsList);
        context.startService(intent);

    }



    private void handleActionWidgetService(ArrayList<Ingredients> receivedIngredientObjects) {
        Intent intent = new Intent(UPDATE_WIDGET);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putParcelableArrayListExtra(GET_INGRIDENTS,receivedIngredientObjects);
        sendBroadcast(intent);
    }

}


