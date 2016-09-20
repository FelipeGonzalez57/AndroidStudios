package com.example.pandithagd.solucion5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;


/**
 * Created by PandithaGD on 19/09/2016.
 */
public class Edificio implements Casa{
    @Override
    public void dibujar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(100, 220, 150, 150));
        paint.setStyle(Paint.Style.FILL);


        float mitadAncho = canvas.getWidth()/2;
        float mitadAlto = canvas.getHeight()/2;
        float mitadX = canvas.getWidth()/2;
        float mitadY = canvas.getHeight()/2;

        canvas.drawRect(mitadX*0.5f,mitadY*0.4f,
                mitadX*1.5f,mitadY*3.5f, paint);

        Paint paint3 = new Paint();
        paint3.setColor(Color.argb(150, 36, 180, 30));
        float mitadAnch = canvas.getWidth()/2;
        float mitadAlt = canvas.getHeight()/2;
        float mitadXA= canvas.getWidth()/2;
        float mitadYB = canvas.getHeight()/2;

        paint3.setStyle(Paint.Style.FILL);
        canvas.drawRect(mitadX*1.0f,mitadY*1.5f,
                mitadX*1.5f,mitadY*3.5f, paint3);











        Path path = new Path();
        Paint paint2 = new Paint();
        paint2.setColor(Color.argb(128, 220, 100, 50));
        paint2.setStyle(Paint.Style.FILL);

        paint.setColor(Color.argb(255, 240, 178, 72));
        paint.setStyle(Paint.Style.FILL);

        float mitad = canvas.getWidth()/2;
        float min = Math.min(canvas.getWidth(),
                canvas.getHeight());
        float half = min/2;

        mitad = mitad - half;

        path.moveTo(mitad+half*0.5f, half*0.84f);
        path.lineTo(mitad+half*1.5f, half*0.84f);
        path.lineTo(mitad+half*0.68f, half*1.45f);
        path.lineTo(mitad+half*1.0f, half*0.5f);
        path.lineTo(mitad+half*1.32f, half*1.45f);
        path.lineTo(mitad+half*0.5f, half*0.84f);

        path.close();
        canvas.drawPath(path, paint);

    }
    }

