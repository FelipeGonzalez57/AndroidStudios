package com.example.pandithagd.solucion5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private EditText edtCasa;
    private Button btnCrear;
    private CasaFactory factory;
    private Casa figura;
    private Lienzo lienzo;
    private LinearLayout layPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





            edtCasa = (EditText)findViewById(R.id.edt_Casa);
            btnCrear = (Button)findViewById(R.id.btn_crear);
            layPrincipal = (LinearLayout)findViewById(R.id.lay_principal);
            lienzo = new Lienzo(MainActivity.this, figura);

            factory = new CasaFactory();

            layPrincipal.addView(lienzo);

            btnCrear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    figura = factory.crearCasa(
                            edtCasa.getText().toString());
                    lienzo.setCasa(figura);
                    lienzo.invalidate();
                }
            });


        }
}
