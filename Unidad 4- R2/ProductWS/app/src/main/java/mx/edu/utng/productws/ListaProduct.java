package mx.edu.utng.productws;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by PandithaGD on 20/04/2017.
 */

public class ListaProduct extends ListActivity{

    final String NAMESPACE = "http://ws.utng.edu.mx";

    final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

    private ArrayList<Product> products = new ArrayList<Product>();
    private int idSelected;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SelectProduct select=new SelectProduct();
        select.execute();
        registerForContextMenu(getListView());

    }//end OnCreate


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_modificar:

                Product product = products.get(selectedPosition);
                Bundle bundleProduct = new Bundle();
                for (int i = 0; i < product.getPropertyCount(); i++) {
                    bundleProduct.putString("valor" + i, product.getProperty(i).toString());
                }
                bundleProduct.putString("accion", "modificar");
                Intent intent = new Intent(ListaProduct.this, MainActivity.class);
                intent.putExtras(bundleProduct);
                startActivity(intent);

                return true;
            case R.id.item_eliminar:
                DeleteProduct eliminar = new DeleteProduct();
                eliminar.execute();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }//End  OnContext

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_regresar:
                startActivity(new Intent(ListaProduct.this, MainActivity.class));
                break;
            default:
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }//End onMenuItem


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(getListView().getAdapter().getItem(info.position).toString());
        idSelected = (Integer) products.get(info.position).getProperty(0);
        selectedPosition = info.position;

        inflater.inflate(R.menu.menu_contextual, menu);

    }//


    ///Clase estatica

    private class SelectProduct extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... params) {

            boolean result = true;

            final String METHOD_NAME = "getProducts";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            products.clear();
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(MainActivity.URL);

            try {
                transporte.call(SOAP_ACTION, envelope);
                Vector<SoapObject> response = (Vector<SoapObject>) envelope.getResponse();
                if (response != null) {

                    for (SoapObject objSoap : response) {
                        Product product = new Product();
                        ///Estos se ponene como el modelo de android

                        product.setProperty(0, Integer.parseInt(objSoap.getProperty("id").toString()));

                        product.setProperty(1, objSoap.getProperty("productName").toString());
                        product.setProperty(2, objSoap.getProperty("supplierId").toString());
                        product.setProperty(3, objSoap.getProperty("categoryId").toString());
                        product.setProperty(4, objSoap.getProperty("quantityPerUnit").toString());
                        product.setProperty(5, objSoap.getProperty("unitPrice").toString());
                        product.setProperty(6, objSoap.getProperty("unitsInStock").toString());
                        product.setProperty(7, objSoap.getProperty("unitsOnOrder").toString());
                        product.setProperty(8, objSoap.getProperty("reorderLevel").toString());
                        product.setProperty(9, objSoap.getProperty("discontinued").toString());

                        products.add(product);
                    }
                }

            } catch (XmlPullParserException e) {
                Log.e("Error XMLPullParser", e.toString());
                result = false;
            } catch (HttpResponseException e) {
                Log.e("Error HTTP", e.toString());
                result = false;
            } catch (IOException e) {
                Log.e("Error IO", e.toString());
                result = false;
            } catch (ClassCastException e) {

                //Enviará aquí cuando exista un solo registro en la base.
                try {
                    SoapObject objSoap = (SoapObject) envelope.getResponse();
                    Product product = new Product();

                    product.setProperty(0, Integer.parseInt(objSoap.getProperty("id").toString()));

                    product.setProperty(1, objSoap.getProperty("productName").toString());
                    product.setProperty(2, objSoap.getProperty("supplierId").toString());
                    product.setProperty(3, objSoap.getProperty("categoryId").toString());
                    product.setProperty(4, objSoap.getProperty("quantityPerUnit").toString());
                    product.setProperty(5, objSoap.getProperty("unitPrice").toString());
                    product.setProperty(6, objSoap.getProperty("unitsInStock").toString());
                    product.setProperty(7, objSoap.getProperty("unitsOnOrder").toString());
                    product.setProperty(8, objSoap.getProperty("reorderLevel").toString());
                    product.setProperty(9, objSoap.getProperty("discontinued").toString());


                    products.add(product);
                } catch (SoapFault e1) {
                    Log.e("Error SoapFault", e.toString());
                    result = false;
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                final String[] datos = new String[products.size()];
                for (int i = 0; i < products.size(); i++) {
                    datos[i] = products.get(i).getProperty(0) + " - "
                            + products.get(i).getProperty(1) + " - "
                            + products.get(i).getProperty(2) + " - "
                            + products.get(i).getProperty(3) + " - "
                            + products.get(i).getProperty(4) + " - "
                            + products.get(i).getProperty(5) + " - "
                            + products.get(i).getProperty(6) + " - "
                            + products.get(i).getProperty(7) + " - "
                            + products.get(i).getProperty(8) + " - "
                            + products.get(i).getProperty(9);
                }
//////////////////////////////////este layout
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(ListaProduct.this, android.R.layout.simple_list_item_1, datos);
                setListAdapter(adaptador);
            } else {
                Toast.makeText(getApplicationContext(), "No se encontraron datos.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }//






    ///Clase estatica For Delete

    private class DeleteProduct extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;

            final String METHOD_NAME = "removePrdocut";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("id", idSelected);

            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(MainActivity.URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive resultado_xml = (SoapPrimitive) envelope
                        .getResponse();
                String res = resultado_xml.toString();

                if (!res.equals("0")){
                    result = true;}

            } catch (Exception e) {
                Log.e("Error", e.toString());
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(),
                        "Eliminado", Toast.LENGTH_SHORT).show();
                SelectProduct consulta = new SelectProduct();
                consulta.execute();
            } else {
                Toast.makeText(getApplicationContext(), "Error al eliminar",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
}//End class
