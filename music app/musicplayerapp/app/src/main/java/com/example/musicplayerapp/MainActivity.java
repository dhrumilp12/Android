package com.example.musicplayerapp;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ImageButton btnPlayAll;
    private ArrayList<File> mySongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView   = findViewById(R.id.listView);
        btnPlayAll = findViewById(R.id.btn_play_all);

        String permission = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                ? Manifest.permission.READ_MEDIA_AUDIO
                : Manifest.permission.READ_EXTERNAL_STORAGE;

        Dexter.withContext(this)
                .withPermission(permission)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mySongs = fetchSongs(Environment.getExternalStorageDirectory());
                        if (mySongs.isEmpty()) {
                            Toast.makeText(MainActivity.this, "No songs found!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String[] items = new String[mySongs.size()];
                        for (int i = 0; i < mySongs.size(); i++) {
                            items[i] = mySongs.get(i).getName().replace(".mp3", "");
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                items
                        );
                        listView.setAdapter(adapter);

                        // Play selected song
                        listView.setOnItemClickListener((parent, view, position, id) ->
                                startPlayerActivity(position, items[position])
                        );

                        // Play entire playlist from the first song, with a Toast
                        btnPlayAll.setOnClickListener(v -> {
                            Toast.makeText(MainActivity.this,
                                    "Playing full playlist", Toast.LENGTH_SHORT).show();
                            startPlayerActivity(0, items[0]);
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity.this,
                                "Permission Denied. Cannot access audio files.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest request,
                                                                   PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void startPlayerActivity(int position, String songName) {
        Intent intent = new Intent(this, PlaySong.class);
        intent.putExtra("songList", mySongs);
        intent.putExtra("currentSong", songName);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private ArrayList<File> fetchSongs(File directory) {
        ArrayList<File> songList = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && !file.isHidden()) {
                    songList.addAll(fetchSongs(file));
                } else if (file.getName().endsWith(".mp3")
                        && !file.getName().startsWith(".")) {
                    songList.add(file);
                }
            }
        }
        return songList;
    }
}
