package mx.edu.utng.restauranteapp;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;
import androidx.room.Entity;


@Entity
public class Restaurante {

    @PrimaryKey
    @NonNull
    private String nombre;
    private String urlPhoto;
    private float valoracion;
    private String direccion;

    public Restaurante() {
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
