package com.example.pandithagd.solucion4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edt_nombre;
    private EditText edt_cantidad;
    private EditText edt_precio;
    private Button btn_clonar;
    private GridView grvPandas;
    private ArrayList<Panda> pandas;
    private Panda panda;
    private PandaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_nombre=(EditText)findViewById(R.id.edt_nombre);
        edt_cantidad =(EditText)findViewById(R.id.edt_cantidad);
        edt_precio =(EditText)findViewById(R.id.edt_precio);
        btn_clonar=(Button)findViewById(R.id.btn_clonar);
        grvPandas = (GridView)findViewById(R.id.grv_pandas);
        pandas = new ArrayList<Panda>();
        panda = new Panda();
        btn_clonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panda.setNombre(edt_nombre.getText().toString());
                panda.setCantidad(Integer.parseInt(edt_cantidad.getText().toString()));
                panda.setPrecio(Float.parseFloat(edt_precio.getText().toString()));
                Panda clon = (Panda) panda.clonar();
                pandas.add(clon);

                adapter = new PandaAdapter(MainActivity.this,pandas);
                grvPandas.setAdapter(adapter);
            }
        });

    }
}
