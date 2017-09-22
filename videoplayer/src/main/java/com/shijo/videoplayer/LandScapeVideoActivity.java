package com.shijo.videoplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class LandScapeVideoActivity extends AppCompatActivity {

    String url = "";
    Uri uri;
    VideoView videoView;
    ImageView playBtn;
    ImageView volumeBtn;
    ImageView pauseBtn;
    ImageView fullScreenBtn;
    ImageView muteBtn;
    TextView timeTxt;
    RelativeLayout relativeLayout;
    long videoDuration;
    int current = 0;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_landscape_activity);

        url =   getIntent().getExtras().getString("url");

        playBtn     =   (ImageView) findViewById(R.id.play_button);
        volumeBtn   =   (ImageView) findViewById(R.id.volume_btn);
        muteBtn     =   (ImageView) findViewById(R.id.volume_mute_btn);
        timeTxt     =   (TextView) findViewById(R.id.time);
        pauseBtn    =   (ImageView) findViewById(R.id.pause_button);
        relativeLayout = (RelativeLayout) findViewById(R.id.video_view_layout);
        fullScreenBtn  = (ImageView) findViewById(R.id.full_screen_btn);
        videoView   =   (VideoView) findViewById(R.id.video_view);

        videoView.setVideoPath(url);
        videoView.seekTo(1000);
        videoView.setClickable(true);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer =   mp;
                videoDuration = videoView.getDuration();
                timeTxt.setText((videoDuration/1000)/60 + "m:"+(videoDuration/1000)%60+"s");

            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setbuttonLayout();
            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setbuttonLayout();
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
                playBtn.setVisibility(View.GONE);
                startCoonterforTime();
            }
        });

        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volumeBtn.setVisibility(View.GONE);
                muteBtn.setVisibility(View.VISIBLE);
                mediaPlayer.setVolume(0,0);
            }
        });

        muteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volumeBtn.setVisibility(View.VISIBLE);
                muteBtn.setVisibility(View.GONE);
                mediaPlayer.setVolume((float) 0.15051,(float) 0.150514);
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseBtn.setVisibility(View.GONE);
                playBtn.setVisibility(View.VISIBLE);
                videoView.pause();
            }
        });

        fullScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }

    private void setbuttonLayout() {

        if(videoView.isPlaying()){
            playBtn.setVisibility(View.GONE);
            pauseBtn.setVisibility(View.VISIBLE);
        }else {
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
        }

        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){

            }
            public  void onFinish(){
                playBtn.setVisibility(View.GONE);
                pauseBtn.setVisibility(View.GONE);
            }
        }.start();
    }

    private void startCoonterforTime() {

        new CountDownTimer(videoDuration, 1000){
            public void onTick(long millisUntilFinished){
                long current = videoView.getCurrentPosition();

                timeTxt.setText(((videoDuration-current)/1000)/60 + "m:"+((videoDuration-current)/1000)%60+"s");
            }
            public  void onFinish(){
                timeTxt.setText("0m:0s");
            }
        }.start();
    }
}
