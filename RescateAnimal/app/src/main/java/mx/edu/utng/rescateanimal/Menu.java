package mx.edu.utng.rescateanimal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    TextView userName;
    private ImageButton btnSOS,btnLogros,btnSOSPerros,btnSOSGatos,btnSOSOtros,btnRedes,btnAjustes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        userName=(TextView)findViewById(R.id.user_name);
        Bundle extras = getIntent().getExtras();
        String nameUser = extras.getString("usuario");

        userName.setText(nameUser);

        btnSOS = (ImageButton) findViewById(R.id.btnSOS);
        btnLogros = (ImageButton) findViewById(R.id.btnLogros);
        btnSOSPerros = (ImageButton) findViewById(R.id.btnSOSPerros);
        btnSOSGatos = (ImageButton) findViewById(R.id.btnSOSGatos);
        btnSOSOtros = (ImageButton) findViewById(R.id.btnSOSOtros);
        btnRedes = (ImageButton) findViewById(R.id.btnRedes);
        btnAjustes = (ImageButton) findViewById(R.id.user_photo);

    btnSOS.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intentLogin = new Intent(Menu.this, Trabajando.class);
            startActivity(intentLogin);
        }
    });

    btnLogros.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intentLogin = new Intent(Menu.this, Trabajando.class);
            startActivity(intentLogin);
        }
    });
    btnSOSPerros.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentLogin = new Intent(Menu.this, perrosPerdidos.class);
            startActivity(intentLogin);
        }
    });

    btnSOSGatos.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentLogin = new Intent(Menu.this, gatosPerdidos.class);
            startActivity(intentLogin);
        }
    });

    btnSOSOtros.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentLogin = new Intent(Menu.this, Trabajando.class);
            startActivity(intentLogin);
        }
    });

    btnRedes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentLogin = new Intent(Menu.this, RedesSociales.class);
            startActivity(intentLogin);
        }
    });

    btnAjustes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentLogin = new Intent(Menu.this, Trabajando.class);
            startActivity(intentLogin);
        }
    });
    }

}

