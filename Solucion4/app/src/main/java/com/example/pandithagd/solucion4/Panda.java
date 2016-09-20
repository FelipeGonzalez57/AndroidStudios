package com.example.pandithagd.solucion4;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class Panda implements  Clonable{
    private  String nombre;
    private int cantidad;
    private float precio;

    public Panda(){
        this.nombre="";
        this.cantidad = 0;
        this.precio = 0.0f;
    }

    public Panda(String nombre, int cantidad, float precio){
        this.nombre=nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Override
    public Clonable clonar() {
        Panda clon = new Panda();
        clon.nombre=nombre;
        clon.cantidad = cantidad;
        clon.precio = precio;
        return clon;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }
}
