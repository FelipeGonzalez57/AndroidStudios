package com.example.pandithagd.ordinario1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by PandithaGD on 19/01/2017.
 */
public class SecondActivity extends AppCompatActivity{
        private EditText edtNumber1;
        private EditText edtNumber2;
        private Button btnCalculate;
        private Spinner spiOperation1;
        private TextView txvResult;
    private Button btnOtherO;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.second_main);

            edtNumber1=(EditText) findViewById(R.id.edt_number1);
            edtNumber2=(EditText) findViewById(R.id.edt_number2);
            btnCalculate=(Button)findViewById(R.id.btn_calculate);

            txvResult=(TextView) findViewById(R.id.txv_result);
            btnOtherO=(Button)findViewById(R.id.btn_other_o);



            btnCalculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double number1=Double.parseDouble(edtNumber1.getText().toString());
                    double number2=Double.parseDouble(edtNumber2.getText().toString());
                    double result=0;

                    result=(Math.sqrt(number1) - Math.sqrt(number2))*(Math.sqrt(number1)+ Math.sqrt(number2));

                    txvResult.setText(""+result);
                }
            });
            btnOtherO.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                }
            });
        }
}
