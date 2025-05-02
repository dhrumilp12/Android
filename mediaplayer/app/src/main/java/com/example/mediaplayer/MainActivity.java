package com.example.mediaplayer;


import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    ListView listView;
    ArrayList<File> mySongs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);




        String permission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission = Manifest.permission.READ_MEDIA_AUDIO;
        } else {
            permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        }


        Dexter.withContext(this)
                .withPermission(permission)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        ArrayList<File> mySongs = fetchSongs(Environment.getExternalStorageDirectory());




                        if (mySongs.isEmpty()) {
                            Toast.makeText(MainActivity.this, "No songs found!", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        String[] items = new String[mySongs.size()];
                        for (int i = 0; i < mySongs.size(); i++) {
                            items[i] = mySongs.get(i).getName().replace(".mp3", "");
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, items);
                        listView.setAdapter(adapter);


                        listView.setOnItemClickListener((parent, view, position, id) -> {
                            Intent intent = new Intent(MainActivity.this, mediaplayer.class);
                            intent.putExtra("songList", mySongs);
                            intent.putExtra("currentSong", items[position]);
                            intent.putExtra("position", position);
                            startActivity(intent);
                        });
                    }


                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity.this, "Permission Denied. Cannot access audio files.", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest request, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();


    }
    public ArrayList<File> fetchSongs(File directory) {
        ArrayList<File> songList = new ArrayList<>();
        File[] files = directory.listFiles();


        if (files != null) {
            for (File file : files) {
                if (!file.isHidden() && file.isDirectory()) {
                    songList.addAll(fetchSongs(file)); // Recursive
                } else if (file.getName().endsWith(".mp3") && !file.getName().startsWith(".")) {
                    songList.add(file);
                }
            }
        }
        return songList;
    }
}
