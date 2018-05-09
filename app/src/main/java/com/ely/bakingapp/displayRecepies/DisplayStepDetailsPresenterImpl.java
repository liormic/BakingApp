package com.ely.bakingapp.displayRecepies;

import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

/**
 * Created by Lior on 4/30/2018.
 */

public class DisplayStepDetailsPresenterImpl implements DisplayStepDetailsPresenter {
    DisplayStepDetailsFragmentView view;
    @Override
    public void initMediaSeesion(MediaSessionCompat mediaSessionCompat) {
        mediaSessionCompat.setFlags(mediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |mediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSessionCompat.setMediaButtonReceiver(null);
        PlaybackStateCompat.Builder stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSessionCompat.setPlaybackState(stateBuilder.build());
        mediaSessionCompat.setCallback(new DisplayStepDetailsFragment.sessionCallBack());
        mediaSessionCompat.setActive(true);
    }

    @Override
    public void releasePlayer() {
        DisplayStepDetailsFragment.simpleExoPlayer.stop();
        DisplayStepDetailsFragment.simpleExoPlayer.release();
        DisplayStepDetailsFragment.simpleExoPlayer = null;
    }

    @Override
    public void setView(DisplayStepDetailsFragmentView view) {
        this.view = view;
    }
}
