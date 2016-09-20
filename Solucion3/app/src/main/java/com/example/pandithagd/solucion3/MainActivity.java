package com.example.pandithagd.solucion3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

        private EditText edt_revista;
        private EditText edt_color;
        private EditText edt_cantidad;
        private EditText edt_precio;
        private Button btn_clonar;
        private GridView grvRevista;
        private ArrayList<Revista> revistas;
        private Revista revista;
        private RevistaAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_revista = (EditText)findViewById(R.id.edt_revista);
        edt_cantidad =(EditText)findViewById(R.id.edt_cantidad);
        edt_precio =(EditText)findViewById(R.id.edt_precio);
        btn_clonar=(Button)findViewById(R.id.btn_clonar);
        grvRevista = (GridView)findViewById(R.id.grv_revistas);
        revistas = new ArrayList<Revista>();
        revista = new Revista();
        btn_clonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revista.setRevista(edt_revista.getText().toString());
                revista.setCantidad(Integer.parseInt(edt_cantidad.getText().toString()));
                revista.setPrecio(Float.parseFloat(edt_precio.getText().toString()));
                Revista clon = (Revista)revista.clonar();
                revistas.add(clon);

                adapter = new RevistaAdapter(MainActivity.this,revistas);
                grvRevista.setAdapter(adapter);
            }
        });
    }
}
