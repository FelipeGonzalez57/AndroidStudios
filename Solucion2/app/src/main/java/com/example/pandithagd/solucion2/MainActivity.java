package com.example.pandithagd.solucion2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombreP;
    private EditText edtNombreD;
    private EditText edtTipo;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNombreP = (EditText) findViewById(R.id.edt_nombreP);
        edtNombreD = (EditText) findViewById(R.id.edt_nombreD);
        edtTipo = (EditText) findViewById(R.id.edt_tipo);
        btnCrear = (Button) findViewById(R.id.btn_crear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pelicula pelicula = Pelicula.getPelicula();
                pelicula.setNombreP(edtNombreP.getText().toString());
                pelicula.setNombreD(edtNombreD.getText().toString());
                pelicula.setTipo(edtTipo.getText().toString());

                Toast.makeText(MainActivity.this, "Pelicula: "
                                + "\nNombre de la Pelicula: " + pelicula.getNombreP()
                                + "\nNombre del Director: " + pelicula.getNombreD()
                                + "\nTipo de Pelicula: " + pelicula.getTipo(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
