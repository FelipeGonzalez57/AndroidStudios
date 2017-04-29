package mx.edu.utng.citews;

/**
 * Created by PandithaGD on 27/04/2017.
 */

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
public class ListaCite extends ListActivity {
    final String NAMESPACE = "http://ws.utng.edu.mx";

    final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);


    private ArrayList<Cite> cites = new ArrayList<Cite>();
    private int idSelected;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SelectCite select=new SelectCite();
        select.execute();
        registerForContextMenu(getListView());

    }//end OnCreate


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_modificar:

                Cite cite = cites.get(selectedPosition);
                Bundle bundleCite = new Bundle();
                for (int i = 0; i < cite.getPropertyCount(); i++) {
                    bundleCite.putString("valor" + i, cite.getProperty(i).toString());
                }
                bundleCite.putString("accion", "modificar");
                Intent intent = new Intent(ListaCite.this, MainActivity.class);
                intent.putExtras(bundleCite);
                startActivity(intent);

                return true;
            case R.id.item_eliminar:
                DeleteCite eliminar = new DeleteCite();
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
                startActivity(new Intent(ListaCite.this, MainActivity.class));
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
        idSelected = (Integer) cites.get(info.position).getProperty(0);
        selectedPosition = info.position;

        inflater.inflate(R.menu.menu_contextual, menu);

    }//


    ///Clase estatica

    private class SelectCite extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... params) {

            boolean result = true;

            final String METHOD_NAME = "getCites";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            cites.clear();
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(MainActivity.URL);

            try {
                transporte.call(SOAP_ACTION, envelope);
                Vector<SoapObject> response = (Vector<SoapObject>) envelope.getResponse();
                if (response != null) {

                    for (SoapObject objSoap : response) {
                        Cite cite = new Cite();
                        ///Estos se ponene como el modelo de android

                        cite.setProperty(0, Integer.parseInt(objSoap.getProperty("id").toString()));
                        cite.setProperty(1, objSoap.getProperty("usuario").toString());
                        cite.setProperty(2, objSoap.getProperty("room").toString());
                        cite.setProperty(3, objSoap.getProperty("recurrenceParent").toString());

                        cite.setProperty(4, objSoap.getProperty("subject").toString());
                        cite.setProperty(5, objSoap.getProperty("inicio").toString());
                        cite.setProperty(6, objSoap.getProperty("finall").toString());
                        cite.setProperty(7, objSoap.getProperty("recurrenceRule").toString());
                        cite.setProperty(8, objSoap.getProperty("annotations").toString());
                        cite.setProperty(9, objSoap.getProperty("descripcion").toString());
                        cite.setProperty(10, objSoap.getProperty("reminder").toString());

                        cites.add(cite);
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
                    Cite cite = new Cite();

                    cite.setProperty(0, Integer.parseInt(objSoap.getProperty("id").toString()));
                    cite.setProperty(1, objSoap.getProperty("usuario").toString());
                    cite.setProperty(2, objSoap.getProperty("room").toString());
                    cite.setProperty(3, objSoap.getProperty("recurrenceParent").toString());

                    cite.setProperty(4, objSoap.getProperty("subject").toString());
                    cite.setProperty(5, objSoap.getProperty("inicio").toString());
                    cite.setProperty(6, objSoap.getProperty("finall").toString());
                    cite.setProperty(7, objSoap.getProperty("recurrenceRule").toString());
                    cite.setProperty(8, objSoap.getProperty("annotations").toString());
                    cite.setProperty(9, objSoap.getProperty("descripcion").toString());
                    cite.setProperty(10, objSoap.getProperty("reminder").toString());

                    cites.add(cite);
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
                final String[] datos = new String[cites.size()];
                for (int i = 0; i < cites.size(); i++) {
                    datos[i] = cites.get(i).getProperty(0) + " - "
                            + cites.get(i).getProperty(1) + " - "
                            + cites.get(i).getProperty(2) + " - "
                            + cites.get(i).getProperty(3) + " - "
                            + cites.get(i).getProperty(4) + " - "
                            + cites.get(i).getProperty(5) + " - "
                            + cites.get(i).getProperty(6) + " - "
                            + cites.get(i).getProperty(7) + " - "
                            + cites.get(i).getProperty(8) + " - "
                            + cites.get(i).getProperty(9) + " - "
                            + cites.get(i).getProperty(10);
                }
//////////////////////////////////este layout
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(ListaCite.this, android.R.layout.simple_list_item_1, datos);
                setListAdapter(adaptador);
            } else {
                Toast.makeText(getApplicationContext(), "No se encontraron datos.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }//






    ///Clase estatica For Delete

    private class DeleteCite extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;

            final String METHOD_NAME = "removeCite";
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
                SelectCite consulta = new SelectCite();
                consulta.execute();
            } else {
                Toast.makeText(getApplicationContext(), "Error al eliminar",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
}
