package com.ely.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Lior on 5/2/2018.
 */

public class RecepieRemoteViewWidget extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new RecepieWidgetRemoteViewFactory(this.getApplicationContext(),intent);
    }


}
