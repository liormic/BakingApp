package com.ely.bakingapp.displayRecepies;

import android.net.Uri;
import android.support.v4.media.session.PlaybackStateCompat;

/**
 * Created by Lior on 4/25/2018.
 */

public interface DisplayStepDetailsFragmentView {

    void createNotification(PlaybackStateCompat state);
    void initPlayer(Uri uri);
}
