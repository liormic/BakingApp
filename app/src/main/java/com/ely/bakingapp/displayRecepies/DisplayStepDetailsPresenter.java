package com.ely.bakingapp.displayRecepies;

import android.support.v4.media.session.MediaSessionCompat;

/**
 * Created by Lior on 4/30/2018.
 */

public interface DisplayStepDetailsPresenter {
    void initMediaSeesion(MediaSessionCompat mediaSessionCompat);
    void releasePlayer();
    void setView(DisplayStepDetailsFragmentView view);
}
