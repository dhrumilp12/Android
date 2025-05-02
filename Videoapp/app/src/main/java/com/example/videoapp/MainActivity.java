package com.example.videoapp;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private SeekBar seekBar;
    private Button btnPlay, btnPause;
    private Handler handler = new Handler();

    private Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            if (videoView.isPlaying()) {
                seekBar.setProgress(videoView.getCurrentPosition());
            }
            handler.postDelayed(this, 1000); // update progress every second
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // optional if you're using it
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        seekBar = findViewById(R.id.seekBar);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);

        // Set your video URL here
        Uri videoUri = Uri.parse("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4");
        videoView.setVideoURI(videoUri);

        // Once the video is prepared, set the SeekBar max and start updating
        videoView.setOnPreparedListener(mp -> {
            seekBar.setMax(videoView.getDuration());
            videoView.start();
            handler.postDelayed(updateSeekBar, 0);
        });

        // Play button
        btnPlay.setOnClickListener(v -> {
            if (!videoView.isPlaying()) {
                videoView.start();
            }
        });

        // Pause button
        btnPause.setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.pause();
            }
        });

        // SeekBar changes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                }
            }
            @Override public void onStartTrackingTouch(SeekBar sb) { }
            @Override public void onStopTrackingTouch(SeekBar sb) { }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateSeekBar);
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
    }
}
