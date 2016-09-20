package com.example.pandithagd.solucion4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class PandaAdapter extends ArrayAdapter<Panda> {

        public PandaAdapter(Context context, ArrayList<Panda>panda){super(context,0,panda); }

        public View getView(int position, View convertView, ViewGroup parent){
            Panda panda = getItem(position);
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.pandas_layout,parent,false);
            }
            TextView txvPanda = (TextView)convertView.findViewById(R.id.tvx_panda);
            txvPanda.setText(panda.getNombre()+" "+panda.getCantidad()+" "+panda.getPrecio());

            return convertView;


        }
}
