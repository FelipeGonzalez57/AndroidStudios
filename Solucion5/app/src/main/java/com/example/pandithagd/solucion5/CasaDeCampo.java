package com.example.pandithagd.solucion5;

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class CasaDeCampo implements  Casa{
    public void dibujar(Canvas canvas) {
    Paint paint = new Paint();
        paint.setColor(Color.argb(175, 36, 153, 150));
        paint.setStyle(Paint.Style.FILL);



    Path path = new Path();
        Paint paint2 = new Paint();
        paint2.setColor(Color.argb(128, 50, 200, 100));
        paint2.setStyle(Paint.Style.FILL);

    float mitadAncho = canvas.getWidth()/2;
    float mitadAlto = canvas.getHeight()/2;
        float mitadX = canvas.getWidth()/2;
        float mitadY = canvas.getHeight()/2;

        canvas.drawRect(mitadX*0.5f,mitadY*1.0f,
                mitadX*1.5f,mitadY*2.5f, paint2);

    path.moveTo(mitadAncho, mitadAlto*0.5f);
    path.lineTo(mitadAncho*0.5f, mitadAlto*1.0f);
    path.lineTo(mitadAncho*1.5f, mitadAlto*1.0f);
    path.lineTo(mitadAncho, mitadAlto*0.5f);
    path.close();

    canvas.drawPath(path, paint);
}


}
