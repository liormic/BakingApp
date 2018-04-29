package com.ely.bakingapp.displayRecepies;

import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;

import com.ely.bakingapp.R;
import com.ely.bakingapp.RecepieObject;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.EventListener;

import butterknife.BindView;
import okhttp3.internal.Util;

public class DisplayStepDetailsActivity extends AppCompatActivity {
    @BindView(R.id.player_view) PlayerView exoPlayerView;
    private SimpleExoPlayer simpleExoPlayer;
    private static MediaSessionCompat mediaSessionCompat;
    private ArrayList<RecepieObject> recepieObjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recepie_detail);

        recepieObjects = getIntent().getParcelableArrayListExtra(getResources().getString(R.string.recepies));
        int stepPosition = getIntent().getIntExtra(getResources().getString(R.string.step_position));
        int clickedStepPosition = getIntent().getIntExtra(getResources().getString(R.string.clicked_step));
        initPlayer(Uri.parse(recepieObjects.get(stepPosition).getSteps().get(clickedStepPosition).getVideoURL());
    }


    private void initPlayer(Uri uri){
        if(simpleExoPlayer!=null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this,trackSelector);
            exoPlayerView.setPlayer(simpleExoPlayer);
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            String userAgent= com.google.android.exoplayer2.util.Util.getUserAgent(this,"BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource.Factory(uri,new DefaultDataSourceFactory(this,userAgent), ,null,null);



        }
    }
}
