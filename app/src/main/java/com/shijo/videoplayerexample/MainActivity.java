package com.shijo.videoplayerexample;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shijo.videoplayer.VideoManager;
import com.shijo.videoplayer.VideoViewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loadFragment();
    }

    private void loadFragment() {

        VideoManager.getInstance().setmActivity(this);
        Bundle bundle = new Bundle();
        bundle.putString("url","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        Fragment videoView = new VideoViewFragment();
        videoView.setArguments(bundle);
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
        fragmentTransaction1.replace(R.id.content_frame, videoView, "video_view_example");
        fragmentTransaction1.commit();
    }
}
