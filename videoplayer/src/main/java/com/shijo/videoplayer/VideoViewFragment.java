package com.shijo.videoplayer;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.shijo.videoplayer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoViewFragment extends Fragment {

    View mView;
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

    MediaPlayer mediaPlayer;

    public VideoViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_video_view, container, false);

        url = getArguments().getString("url");

       uri=Uri.parse(url);

        videoView   =   (VideoView) mView.findViewById(R.id.video_view);
        playBtn     =   (ImageView) mView.findViewById(R.id.play_button);
        volumeBtn   =   (ImageView) mView.findViewById(R.id.volume_btn);
        muteBtn     =   (ImageView) mView.findViewById(R.id.volume_mute_btn);
        timeTxt     =   (TextView) mView.findViewById(R.id.time);
        pauseBtn    =   (ImageView) mView.findViewById(R.id.pause_button);
        relativeLayout = (RelativeLayout) mView.findViewById(R.id.video_view_layout);
        fullScreenBtn  = (ImageView) mView.findViewById(R.id.full_screen_btn);

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
                Intent intent = new Intent(VideoManager.getInstance().getmActivity(),LandScapeVideoActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

        return mView;
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
