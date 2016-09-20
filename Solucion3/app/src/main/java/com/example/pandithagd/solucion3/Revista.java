package com.example.pandithagd.solucion3;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class Revista implements Clonable {
    private String revista;
    private int cantidad;
    private float precio;

    public Revista(){
        this.revista="";
        this.cantidad = 0;
        this.precio = 0.0f;
    }

    public Revista(String revista, int cantidad, float precio){
        this.revista=revista;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Override
    public Clonable clonar() {
        Revista clon = new Revista();
        clon.revista=revista;
        clon.cantidad = cantidad;
        clon.precio = precio;
        return clon;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getRevista() {
        return revista;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public float getCantidad() {
        return cantidad;
    }



    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

