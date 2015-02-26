package com.developer.lab08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by developer on 26/02/2015.
 */
class myAdapter extends BaseAdapter {

    private ArrayList<Ubicaciones> ubicaciones;
    private LayoutInflater layout;

    public myAdapter(Context context, ArrayList<Ubicaciones> objects) {
        this.ubicaciones = objects;
        this.layout = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ubicaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return ubicaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if(convertView == null) {
            holder = new Holder();

            convertView = layout.inflate(R.layout.item,null);

            holder.ubicacion = (TextView) convertView.findViewById(R.id.ubicacion);
            holder.detalles = (TextView) convertView.findViewById(R.id.detalles);

            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }

        holder.ubicacion.setText(ubicaciones.get(position).getUbicacion());
        holder.detalles.setText(ubicaciones.get(position).getDetalles());

        return convertView;
    }
}

class Holder {
    TextView ubicacion;
    TextView detalles;
}