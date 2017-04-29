package mx.edu.utng.citews;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by ANONYMOUS-PC on 27/04/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etUsuario;
    private EditText etRoom;
    private EditText etRecurrenceParent;
    private EditText etSubject;
    private EditText etInicio;
    private EditText etFinal;
    private EditText etRecurrenceRule;
    private EditText etAnnotation;
    private EditText etDescripcion;
    private EditText etReminder;

    private Button btnSave;
    private Button btnList;

    private Cite cite = null;

    final String NAMESPACE ="http://ws.utng.edu.mx";
    final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    //static String URL ="http://192.168.1.75:8080/WebService/services/MovieWS";
    static String URL ="http://192.168.24.69:8080/WSCite/services/CiteWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }


    private void initComponents(){
        etUsuario = (EditText)findViewById(R.id.tv_usuario);
        etRoom = (EditText)findViewById(R.id.tv_room);
        etRecurrenceParent = (EditText)findViewById(R.id.tv_recuperrence);

        etSubject = (EditText)findViewById(R.id.tv_subject);
        etInicio = (EditText)findViewById(R.id.tv_inicio);
        etFinal = (EditText)findViewById(R.id.tv_final);

        etRecurrenceRule = (EditText)findViewById(R.id.tv_recurrence_rule);
        etAnnotation = (EditText)findViewById(R.id.tv_anotation);
        etDescripcion = (EditText)findViewById(R.id.tv_description);

        etReminder = (EditText)findViewById(R.id.tv_remider);


        btnSave = (Button) findViewById(R.id.btn_save);
        btnList = (Button)findViewById(R.id.btn_list);
        btnSave.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consume_w, menu);
        return true;
    }*/
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onClick(View v) {
        if(v.getId()== btnSave.getId()){
            try {
                if (getIntent().getExtras().getString("accion").equals("modificar")) {
                    updateCite tarea = new updateCite();
                    tarea.execute();
                }

            } catch (Exception e) {
                //Cuando no se haya mandado una accion por defecto es insertar.
                InsertCite tarea = new InsertCite();
                tarea.execute();
            }
        }
        if (btnList.getId() == v.getId()) {
            startActivity(new Intent(MainActivity.this, ListaCite.class));
        }
    }

    private class InsertCite extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final String METHOD_NAME = "addCite";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            cite = new Cite();
            cite.setProperty(0, 0);

            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("cite");
            info.setValue(cite);
            info.setType(cite.getClass());
            request.addProperty(info);
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "Cite", Cite.class);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive response =      (SoapPrimitive) envelope.getResponse();
                String res = response.toString();
                if (!res.equals("1")) {
                    result = false;
                }

            } catch (Exception e) {
                Log.e("Error ", e.getMessage());
                result = false;
            }
            return result;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getApplicationContext(),
                        "Registro exitoso.",
                        Toast.LENGTH_SHORT).show();
                cleanBox();

            }else {
                Toast.makeText(getApplicationContext(),
                        "Error al insertar.",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }//
    private void cleanBox(){
        etUsuario.setText("");
        etRoom.setText("");
        etRecurrenceParent.setText("");

        etSubject.setText("");
        etInicio.setText("");
        etFinal.setText("");
        etRecurrenceRule.setText("");
        etAnnotation.setText("");
        etDescripcion.setText("");
        etReminder.setText("");

    }
    private class updateCite extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;

            final String METHOD_NAME = "updateCite";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            cite = new Cite();
            cite.setProperty(0, getIntent().getExtras().getString("valor0"));
            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("cite");
            info.setValue(cite);
            info.setType(cite.getClass());

            request.addProperty(info);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "cite", cite.getClass());


            HttpTransportSE transporte = new HttpTransportSE(URL);

            try {
                transporte.call(SOAP_ACTION,envelope);
                SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                String res = resultado_xml.toString();

                if (!res.equals("1")) {
                    result = false;
                }

            } catch (HttpResponseException e) {
                Log.e("Error HTTP", e.toString());
            } catch (IOException e) {
                Log.e("Error IO", e.toString());
            } catch (XmlPullParserException e) {
                Log.e("Error XmlPullParser", e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(), "Actualizado OK",
                        Toast.LENGTH_SHORT).show();
                cleanBox();
                startActivity(new Intent(MainActivity.this, MainActivity.class));

            } else {
                Toast.makeText(getApplicationContext(), "Error al actualizar",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }//
    private void getData() {
        cite.setProperty(1, etUsuario.getText().toString());
        cite.setProperty(2, etRoom.getText().toString());
        cite.setProperty(3, etRecurrenceParent.getText().toString());

        cite.setProperty(4, etSubject.getText().toString());
        cite.setProperty(5, etInicio.getText().toString());
        cite.setProperty(6, etFinal.getText().toString());
        cite.setProperty(7, etRecurrenceRule.getText().toString());
        cite.setProperty(8, etAnnotation.getText().toString());
        cite.setProperty(9, etDescripcion.getText().toString());
        cite.setProperty(10, etReminder.getText().toString());
    }//

    @Override
    protected void onResume() {
        super.onResume();
        Bundle datosRegreso = this.getIntent().getExtras();
        try {
            //Log.i("Dato", datosRegreso.getString("valor4"));

            etUsuario.setText(datosRegreso.getString("valor1"));
            etRoom.setText(datosRegreso.getString("valor2"));
            etRecurrenceParent.setText(datosRegreso.getString("valor3"));
            etSubject.setText(datosRegreso.getString("valor4"));
            etInicio.setText(datosRegreso.getString("valor5"));
            etFinal.setText(datosRegreso.getString("valor6"));
            etRecurrenceRule.setText(datosRegreso.getString("valor7"));
            etAnnotation.setText(datosRegreso.getString("valor8"));
            etDescripcion.setText(datosRegreso.getString("valor9"));
            etReminder.setText(datosRegreso.getString("valor10"));

        } catch (Exception e) {
            Log.e("Error al Recargar", e.toString());
        }
    }


}
