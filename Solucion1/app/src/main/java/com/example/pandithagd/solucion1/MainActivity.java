package com.example.pandithagd.solucion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private EditText edtNombre;
    private EditText edtTemporada;
    private EditText edtCapitulos;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edt_nombre);
        edtTemporada = (EditText) findViewById(R.id.edt_temporada);
        edtCapitulos =(EditText)findViewById(R.id.edt_capitulos) ;
        btnCrear = (Button) findViewById(R.id.btn_crear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anime anime = Anime.getAnime();
                anime.setNombre(edtNombre.getText().toString());
                anime.setTemporada(
                        Integer.parseInt(
                                edtTemporada.getText().toString()));
                anime.setCapitulos(
                        Integer.parseInt(
                                edtCapitulos.getText().toString()));

               Toast.makeText(MainActivity.this, "Anime: "
                                +"\nNombre: "+anime.getNombre()
                                +"\nNumero de Temporada: "+anime.getTemporada()
                                +"\nNumero de Capitulos: "+anime.getCapitulos() ,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
