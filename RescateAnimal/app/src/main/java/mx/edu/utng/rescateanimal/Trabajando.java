package mx.edu.utng.rescateanimal;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Trabajando  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trabjando);

        TextView texto = (TextView) findViewById(R.id.textView);
        Animation animacion = AnimationUtils.loadAnimation(
                this,R.anim.animacion);

        texto.startAnimation(animacion);
    }
}
