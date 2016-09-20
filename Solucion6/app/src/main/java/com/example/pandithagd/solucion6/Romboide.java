package com.example.pandithagd.solucion6;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class Romboide {
    private int color;

    public Romboide(){}

    public void dibujar(Canvas canvas, int color){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);

        float mitadX = canvas.getWidth()/2;
        float mitadY = canvas.getHeight()/2;

        Path path = new Path();
        path.moveTo(mitadX*1.5f, mitadY*1.0f);
        path.lineTo(mitadX * 1.5f, mitadY*2.0f);
        path.lineTo(mitadX * 0.5f, mitadY);
        path.lineTo(mitadX * 0.5f, mitadY * 0.5f);
        path.lineTo(mitadX * 1.5f, mitadY * 1.5f);
        path.lineTo(mitadX * 1.5f, mitadY*0.5f);
        path.lineTo(mitadX * 1.5f, mitadY*0.5f);
        path.lineTo(mitadX*1.5f, mitadY*0.5f);
        path.lineTo(mitadX*1.5f, mitadY*0.5f);
        path.close();

        canvas.drawPath(path, paint);



    }

    public Memento guardarMemento() {
        return new Memento(color);
    }
    public void restaurarMemento(Memento m) {
        color = m.getColor();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
