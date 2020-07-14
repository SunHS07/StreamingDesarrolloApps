package com.example.app_music_streaming;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolderDatos> {

    ArrayList<Cancion> listDatos;
    private Context context = null;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private boolean initialStage = true;
    String actualBtn ="";

    public AdapterClass(ArrayList<Cancion> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        mediaPlayer = new MediaPlayer();
        View view = LayoutInflater.from(context).inflate(R.layout.item_cancion,null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));

    }


    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView cancion,album,artista;
        ImageView imagenAlbum;
        Button itemPlay;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            cancion = (TextView) itemView.findViewById(R.id.itemCancion);
            album = (TextView) itemView.findViewById(R.id.itemAlbum);
            artista = (TextView) itemView.findViewById(R.id.itemArtista);
            imagenAlbum = (ImageView) itemView.findViewById(R.id.imgAlbum);
            itemPlay = (Button) itemView.findViewById(R.id.btnItemPlayRepro);

        }

        public void asignarDatos(Cancion datos) {

            cancion.setText(datos.getNombreCancion());
            album.setText(datos.getAlbumCancion());
            artista.setText(datos.getArtistaCancion());
            itemPlay.setTag(datos.direccionCancion);

            itemPlay.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(actualBtn.equals("")){
                        actualBtn = itemPlay.getTag().toString();
                    }
                    if(!actualBtn.equals(itemPlay.getTag().toString())){
                        itemPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                        actualBtn = itemPlay.getTag().toString();
                        initialStage = true;
                        playPause= false;
                        mediaPlayer.release();
                    }
                    if(!playPause){
                        if(initialStage){
                                boolean prepared;
                                mediaPlayer = new MediaPlayer();
                                try {
                                    mediaPlayer.setDataSource("http://192.168.0.16/musicstreaming/Musica/" + itemPlay.getTag().toString());
                                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mediaPlayer) {
                                            playPause = false;
                                            itemPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                                            mediaPlayer.stop();
                                            mediaPlayer.reset();
                                        }
                                    });
                                    mediaPlayer.prepare();
                                    prepared = true;
                                }catch (Exception e){
                                    Log.e("AudioStreaming", Objects.requireNonNull(e.getMessage()));
                                    prepared = false;
                                }
                                if (prepared){
                                    mediaPlayer.start();
                                }
                                initialStage = false;
                        }else{
                            if(!mediaPlayer.isPlaying()){
                                mediaPlayer.start();
                            }
                        }
                        itemPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                        Toast.makeText(context, "Reproducir", Toast.LENGTH_SHORT).show();
                        playPause = true;

                    }else{
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.pause();
                        }
                        playPause = false;
                        itemPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                        Toast.makeText(context, "Pausa", Toast.LENGTH_SHORT).show();
                    }

                    /*if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        itemPlay.setBackgroundResource(android.R.drawable.ic_media_play);
                        Toast.makeText(context, "Pausa", Toast.LENGTH_SHORT).show();
                    }else{
                        mediaPlayer.start();
                        itemPlay.setBackgroundResource(android.R.drawable.ic_media_pause);
                        Toast.makeText(context, "Reproducir", Toast.LENGTH_SHORT).show();
                    }*/
                }
            });

        }


    }
}
