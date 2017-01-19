package com.example.pandithagd.ordinario1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edtNumber1;
    private EditText edtNumber2;
    private Button btnCalculate;
    private Spinner spiOperation;
    private TextView txvResult;
    private Button btnOther;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumber1=(EditText) findViewById(R.id.edt_number1);
        edtNumber2=(EditText) findViewById(R.id.edt_number2);
        btnCalculate=(Button)findViewById(R.id.btn_calculate);

        txvResult=(TextView) findViewById(R.id.txv_result);
        btnOther=(Button)findViewById(R.id.btn_other);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float number1=Float.parseFloat(edtNumber1.getText().toString());
                float number2=Float.parseFloat(edtNumber2.getText().toString());
                float result=0;

                 result=(2*number1+5*number2)*(2*number1-5*number2) ;

                txvResult.setText(""+result);
            }
        });
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }
}
