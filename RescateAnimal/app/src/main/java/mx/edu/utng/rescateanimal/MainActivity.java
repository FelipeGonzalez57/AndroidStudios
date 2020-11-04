package mx.edu.utng.rescateanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userName;
    private EditText contraseña;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userName = (EditText) findViewById(R.id.etUserName);
        contraseña = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btLogin);

        btnLogin.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        String user = userName.getText().toString();
        String pass = contraseña.getText().toString();

        if (!user.isEmpty() && !pass.isEmpty()) {
            if (user.equals("felipe") && pass.equals("123")) {
                Intent intentLogin = new Intent(MainActivity.this, Menu.class);
                intentLogin.putExtra("usuario", user);
                startActivity(intentLogin);
            }else{
                Toast.makeText(MainActivity.this, "Usuario o contraseña erronea", Toast.LENGTH_LONG).show();
            }
            } else {
                Toast.makeText(MainActivity.this, "Ingrese un usuario y contraseña", Toast.LENGTH_LONG).show();
            }
        }
    }
