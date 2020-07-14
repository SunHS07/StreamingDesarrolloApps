package com.example.app_music_streaming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainStreaming extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private String idCancion, nombreCancion, albumCancion, artistaCancion, direccionCancion;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_streaming);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        final ArrayList<Cancion> canciones = new ArrayList<Cancion>();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonResponse = json.getJSONArray("cancion");
                    if(jsonResponse.length() > 0){
                        for (int i=0; i < jsonResponse.length(); i++) {
                            JSONObject json_data = jsonResponse.getJSONObject(i);
                            idCancion = json_data.getString("idCancion");
                            nombreCancion = json_data.getString("nombreCancion");
                            albumCancion = json_data.getString("albumCancion");
                            artistaCancion = json_data.getString("artistaCancion");
                            direccionCancion = json_data.getString("direccionCancion");
                            Cancion cancion = new Cancion(idCancion,nombreCancion,albumCancion,artistaCancion,direccionCancion);
                            Log.e("CICLO", cancion.getIdCancion());
                            canciones.add(cancion);

                            AdapterClass adapter =  new AdapterClass(canciones);
                            recyclerView.setAdapter(adapter);
                        }

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainStreaming.this);
                        builder.setMessage("No hay canciones registradas")
                                .setNegativeButton("Reintentar", null)
                                .create().show();
                    }
                }catch (JSONException e ){
                    e.printStackTrace();
                }
            }
        };

        CancionRequest cancionRequest = new CancionRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainStreaming.this);
        queue.add(cancionRequest);




   }
}