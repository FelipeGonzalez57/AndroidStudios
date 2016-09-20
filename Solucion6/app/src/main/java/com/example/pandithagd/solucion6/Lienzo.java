package com.example.pandithagd.solucion6;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class Lienzo extends View {
    private Romboide auto;

    public Lienzo(Context context, Romboide auto){
        super(context);
        this.auto = auto;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(auto!=null){
            auto.dibujar(canvas, auto.getColor());
        }
    }

    public Romboide getAutomovil() {
        return auto;
    }

    public void setAutomovil(Romboide automovil) {
        this.auto = automovil;
    }
}
