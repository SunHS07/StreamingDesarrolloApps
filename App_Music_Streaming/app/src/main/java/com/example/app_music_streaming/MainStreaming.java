package com.example.app_music_streaming;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainStreaming extends AppCompatActivity {

    Button play_pause_music;
    MediaPlayer mp;
    TextView txtTituloCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_streaming);

        play_pause_music = (Button)findViewById(R.id.btnPlayRepro);
        mp = MediaPlayer.create(this, R.raw.everglow);
        txtTituloCancion = (TextView)findViewById(R.id.txtTituloCancionRepro);

        play_pause_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    play_pause_music.setBackgroundResource(android.R.drawable.ic_media_play);
                    Toast.makeText(MainStreaming.this, "Pausa", Toast.LENGTH_SHORT).show();
                    txtTituloCancion.setText("Everglow - Coldplay");
                }else{
                    mp.start();
                    play_pause_music.setBackgroundResource(android.R.drawable.ic_media_pause);
                    Toast.makeText(MainStreaming.this, "Reproducir", Toast.LENGTH_SHORT).show();
                    txtTituloCancion.setText("Everglow - Coldplay");
                }
            }
        });
    }



}