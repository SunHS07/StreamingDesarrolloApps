package com.example.app_music_streaming;

public class Cancion {

    String idCancion, nombreCancion, albumCancion, artistaCancion, direccionCancion;

    public Cancion(String idCancion, String nombreCancion, String albumCancion, String artistaCancion, String direccionCancion) {
        this.idCancion = idCancion;
        this.nombreCancion = nombreCancion;
        this.albumCancion = albumCancion;
        this.artistaCancion = artistaCancion;
        this.direccionCancion = direccionCancion;
    }

    public String getIdCancion() {
        return idCancion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public String getAlbumCancion() {
        return albumCancion;
    }

    public String getArtistaCancion() {
        return artistaCancion;
    }

    public String getDireccionCancion() {
        return direccionCancion;
    }

    public void setIdCancion(String idCancion) {
        this.idCancion = idCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public void setAlbumCancion(String albumCancion) {
        this.albumCancion = albumCancion;
    }

    public void setArtistaCancion(String artistaCancion) {
        this.artistaCancion = artistaCancion;
    }

    public void setDireccionCancion(String direccionCancion) {
        this.direccionCancion = direccionCancion;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "idCancion='" + idCancion + '\'' +
                ", nombreCancion='" + nombreCancion + '\'' +
                ", albumCancion='" + albumCancion + '\'' +
                ", artistaCancion='" + artistaCancion + '\'' +
                ", direccionCancion='" + direccionCancion + '\'' +
                '}';
    }
}
