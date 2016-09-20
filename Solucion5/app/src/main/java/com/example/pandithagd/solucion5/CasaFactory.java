package com.example.pandithagd.solucion5;

import android.graphics.Canvas;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class CasaFactory {
    private Casa casa;
    public Casa crearCasa(String tipo){
        if(tipo!=null) {
            if (tipo.equalsIgnoreCase("casa")) {
                casa = new CasaDeCampo() ;
            } else if (tipo.equalsIgnoreCase("Edificio")) {
                casa = new Edificio();
            }else{
                return  null;
            }
        }
        return casa;
    }
}
