package com.ely.bakingapp.widget;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.ely.bakingapp.RecepieObject;

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
            ArrayList<RecepieObject> recepieObjects = intent.getParcelableArrayListExtra(GET_INGRIDENTS);
            handleActionWidgetService(recepieObjects);

        }
    }

    public static void startRecepieWidgetService(Context context, ArrayList<RecepieObject> recepieObjects) {
        Intent intent = new Intent(context, RecepieWidgetService.class);
        intent.putParcelableArrayListExtra(GET_INGRIDENTS,recepieObjects);
        context.startService(intent);

    }



    private void handleActionWidgetService(ArrayList<RecepieObject> receivedRecepieObjects) {
        Intent intent = new Intent(UPDATE_WIDGET);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putParcelableArrayListExtra(GET_INGRIDENTS,receivedRecepieObjects);
        sendBroadcast(intent);
    }

}


