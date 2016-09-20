package com.example.pandithagd.solucion1;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class Anime {
    private String nombre;
    private int temporada;
    private int capitulos;
    private static Anime anime;

    private  Anime(){
        this.nombre="";
        this.temporada=0;
        this.capitulos=0;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getTemporada() {
        return temporada;
    }

    public static Anime getAnime() {
        if(anime==null){
            anime = new Anime();
        }
        return anime;
    }
}
