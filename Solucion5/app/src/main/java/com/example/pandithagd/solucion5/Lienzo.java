package com.example.pandithagd.solucion5;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class Lienzo extends View {
    private Casa casa;

    public Lienzo(Context context, Casa casa){
        super(context);
        this.casa = casa;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(casa!=null){
            casa.dibujar(canvas);
        }
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa=casa;
    }
}
