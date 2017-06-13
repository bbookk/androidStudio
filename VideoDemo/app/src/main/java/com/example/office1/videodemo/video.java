package com.example.office1.videodemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.VideoView;

public class video extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView video = (VideoView) findViewById(R.id.video_view);
        //load video from sd card
        video.setVideoPath(Environment.getExternalStorageDirectory() + "/wildlife.mp4");
        video.start(); //start video
    }
}
