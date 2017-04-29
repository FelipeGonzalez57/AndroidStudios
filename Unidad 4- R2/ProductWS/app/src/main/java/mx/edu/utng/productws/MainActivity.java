package mx.edu.utng.productws;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etProductName;
    private EditText etSupplierId;
    private EditText etCategoryId;
    private EditText etQuantityPerUnit;
    private EditText etUnitPrice;
    private EditText etUnitsInStock;
    private EditText etUnitsOnOrder;
    private EditText etReorderLevel;
    private EditText etDiscontinued;


    private Button btnSave;
    private Button btnList;

    private Product product = null;

    final String NAMESPACE ="http://ws.utng.edu.mx";
    final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    //static String URL ="http://192.168.1.75:8080/WebService/services/MovieWS";
    static String URL ="http://192.168.24.163:8080/WSCite/services/ProductWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }


    private void initComponents(){
        etProductName = (EditText)findViewById(R.id.tv_product_name);
        etSupplierId = (EditText)findViewById(R.id.tv_supplier_id);
        etCategoryId = (EditText)findViewById(R.id.tv_category_id);
        etQuantityPerUnit = (EditText)findViewById(R.id.tv_quantity_per_unit);
        etUnitPrice = (EditText)findViewById(R.id.tv_unit_price);
        etUnitsInStock = (EditText)findViewById(R.id.tv_unit_in_stock);
        etUnitsOnOrder = (EditText)findViewById(R.id.tv_unit_on_order);
        etReorderLevel = (EditText)findViewById(R.id.tv_reorder_level);
        etDiscontinued = (EditText)findViewById(R.id.tv_discontinued);


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
                    updateProduct tarea = new updateProduct();
                    tarea.execute();
                }

            } catch (Exception e) {
                //Cuando no se haya mandado una accion por defecto es insertar.
                InsertProduct tarea = new InsertProduct();
                tarea.execute();
            }
        }
        if (btnList.getId() == v.getId()) {
            startActivity(new Intent(MainActivity.this, ListaProduct.class));
        }
    }

    private class InsertProduct extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final String METHOD_NAME = "addProduct";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            product = new Product();
            product.setProperty(0, 0);

            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("Product");
            info.setValue(product);
            info.setType(product.getClass());
            request.addProperty(info);
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "Produc", Product.class);

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
        etProductName.setText("");
        etSupplierId.setText("");
        etCategoryId.setText("");
        etQuantityPerUnit.setText("");
        etUnitPrice.setText("");
        etUnitsInStock.setText("");
        etUnitsOnOrder.setText("");
        etReorderLevel.setText("");
        etDiscontinued.setText("");

    }
    private class updateProduct extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;

            final String METHOD_NAME = "updateProduct";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            product = new Product();
            product.setProperty(0, getIntent().getExtras().getString("valor0"));
            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("product");
            info.setValue(product);
            info.setType(product.getClass());

            request.addProperty(info);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "product", product.getClass());


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
        product.setProperty(1, etProductName.getText().toString());
        product.setProperty(2, etSupplierId.getText().toString());
        product.setProperty(3, etCategoryId.getText().toString());
        product.setProperty(4, etQuantityPerUnit.getText().toString());
        product.setProperty(5, etUnitPrice.getText().toString());
        product.setProperty(6, etUnitsInStock.getText().toString());
        product.setProperty(7, etUnitsOnOrder.getText().toString());
        product.setProperty(8, etReorderLevel.getText().toString());
        product.setProperty(9, etDiscontinued.getText().toString());
    }//

    @Override
    protected void onResume() {
        super.onResume();
        Bundle datosRegreso = this.getIntent().getExtras();
        try {
            //Log.i("Dato", datosRegreso.getString("valor4"));

            etProductName.setText(datosRegreso.getString("valor1"));
            etSupplierId.setText(datosRegreso.getString("valor2"));
            etCategoryId.setText(datosRegreso.getString("valor3"));
            etQuantityPerUnit.setText(datosRegreso.getString("valor4"));
            etUnitPrice.setText(datosRegreso.getString("valor5"));
            etUnitsInStock.setText(datosRegreso.getString("valor6"));
            etUnitsOnOrder.setText(datosRegreso.getString("valor7"));
            etReorderLevel.setText(datosRegreso.getString("valor8"));
            etDiscontinued.setText(datosRegreso.getString("valor9"));

        } catch (Exception e) {
            Log.e("Error al Recargar", e.toString());
        }
    }
}
