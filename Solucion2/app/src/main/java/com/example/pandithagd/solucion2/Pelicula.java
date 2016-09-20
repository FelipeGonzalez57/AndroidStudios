package com.example.pandithagd.solucion2;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class Pelicula {
    private String nombreP;
    private String nombreD;
    private String tipo;
    private static Pelicula pelicula;

    private Pelicula(){
        this.nombreP="";
        this.nombreD="";
        this.tipo="";
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }

    public String getNombreD() {
        return nombreD;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static Pelicula getPelicula() {
        if(pelicula==null){
            pelicula = new Pelicula();
        }
        return pelicula;
    }
}
