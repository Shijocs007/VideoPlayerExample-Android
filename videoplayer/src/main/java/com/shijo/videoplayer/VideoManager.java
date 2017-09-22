package com.shijo.videoplayer;

import android.app.Activity;

/**
 * Created by mboxuser on 9/22/2017.
 */

public class VideoManager {

    private static VideoManager _instance;

    Activity mActivity;

    public static VideoManager getInstance() {

        if(_instance == null){
            _instance = new VideoManager();
        }
        return _instance;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
}
