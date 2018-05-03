package com.ely.bakingapp.displayRecepies;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DisplayStepDetailsActivity extends AppCompatActivity implements Player.EventListener, DisplayStepDetailsActivityView {
    public static final String CHANNEL_ID = "NOTIFICATION_ID";
    private static final String TAG = DisplayStepDetailsActivity.class.getCanonicalName();
    public static SimpleExoPlayer simpleExoPlayer;
    private static MediaSessionCompat mediaSessionCompat;
    @BindView(R.id.player_view) PlayerView exoPlayerView;
    @BindView(R.id.recepie_description) TextView recepieDescription;
    @BindView(R.id.next_recepie) Button nextStepButton;
    private PlaybackStateCompat.Builder stateBuilder;
    private ArrayList<RecepieObject> recepieObjects;
    private int stepPosition;
    private String videoUrl;
    private String stepDescriptionText;
    private DisplayStepDetailsPresenterImpl presenter;
    private int clickedStepPosition;
    private boolean isUrlAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recepie_detail);
        ButterKnife.bind(this);
        presenter = new DisplayStepDetailsPresenterImpl();
        presenter.setView(this);
        recepieObjects = getIntent().getParcelableArrayListExtra(getResources().getString(R.string.recepies));
        stepPosition = getIntent().getIntExtra(getResources().getString(R.string.step_position), 0);
        clickedStepPosition = getIntent().getIntExtra(getResources().getString(R.string.clicked_step), 0);
        videoUrl = recepieObjects.get(stepPosition).getSteps().get(clickedStepPosition).getVideoURL();
        stepDescriptionText = recepieObjects.get(stepPosition).getSteps().get(clickedStepPosition).getDescription();


        if (!videoUrl.equals("")) {
            isUrlAvailable = true;
            mediaSessionCompat = new MediaSessionCompat(this, TAG);
            presenter.initMediaSeesion(mediaSessionCompat);
            initPlayer(Uri.parse(videoUrl));
        } else {
            urlNoAvialable();
        }

        if(!stepDescriptionText.equals("")){
          //  recepieDescription.setText(stepDescriptionText);
        }else{
            recepieDescription.setText(getResources().getString(R.string.no_description));
        }

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(simpleExoPlayer!=null) {
                    presenter.releasePlayer();
                }
                if(clickedStepPosition+1 < recepieObjects.get(stepPosition).getSteps().size()) {
                    clickedStepPosition= clickedStepPosition+1;
                    isUrlAvailable =true;
                    initPlayer(Uri.parse(recepieObjects.get(stepPosition).getSteps().get(clickedStepPosition).getVideoURL()));
                    recepieDescription.setText(recepieObjects.get(stepPosition).getSteps().get(clickedStepPosition).getDescription());
                }else{
                    clickedStepPosition = 0;
                    initPlayer(Uri.parse(recepieObjects.get(stepPosition).getSteps().get(clickedStepPosition).getVideoURL()));
                    recepieDescription.setText(recepieObjects.get(stepPosition).getSteps().get(clickedStepPosition).getDescription());
                }


            }

        });

    }


    private void urlNoAvialable(){
        exoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource
                (getResources(), R.drawable.ic_launcher_background));
        exoPlayerView.setUseArtwork(true);
        isUrlAvailable = false;
        Toast.makeText(this, R.string.error_uri, Toast.LENGTH_SHORT).show();
    }


    public void initPlayer(Uri uri) {
        if (simpleExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            exoPlayerView.setPlayer(simpleExoPlayer);
            String userAgent = com.google.android.exoplayer2.util.Util.getUserAgent(this, "BakingApp");
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, userAgent);
            MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isUrlAvailable) {
            mediaSessionCompat.setActive(false);
            presenter.releasePlayer();
        }

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == Player.STATE_READY) && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, simpleExoPlayer.getContentPosition(), 1f);
        } else if (playbackState == Player.STATE_READY) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, simpleExoPlayer.getContentPosition(), 1f);
        }
        mediaSessionCompat.setPlaybackState(stateBuilder.build());
        createNotification(stateBuilder.build());

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    public void createNotification(PlaybackStateCompat state) {
        int icon;
        String play_pause;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        if (state.getState() == PlaybackStateCompat.STATE_PLAYING) {
            icon = R.drawable.exo_controls_pause;
            play_pause = getString(R.string.pause);
        } else {
            icon = R.drawable.exo_controls_play;
            play_pause = getString(R.string.play);
        }

        NotificationCompat.Action playOrPause = new NotificationCompat.Action(icon, play_pause,
                MediaButtonReceiver.buildMediaButtonPendingIntent(this, PlaybackStateCompat.ACTION_PLAY_PAUSE));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, DisplayStepDetailsActivity.class), 0);
        builder.setContentTitle(getString(R.string.video_notification))
                .setContentText(recepieObjects.get(stepPosition).getSteps().get(stepPosition).getShortDescription())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .addAction(playOrPause)
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mediaSessionCompat.getSessionToken())
                        .setShowActionsInCompactView(0, 1));


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }

    public static class sessionCallBack extends MediaSessionCompat.Callback {

        @Override
        public void onPlay() {
            simpleExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            simpleExoPlayer.setPlayWhenReady(false);
        }

    }

    public static class MediaReceiver extends BroadcastReceiver {

        public MediaReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            MediaButtonReceiver.handleIntent(mediaSessionCompat, intent);
        }
    }

}

