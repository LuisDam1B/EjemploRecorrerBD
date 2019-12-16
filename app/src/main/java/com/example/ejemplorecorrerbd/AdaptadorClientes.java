package com.example.ejemplorecorrerbd;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorClientes extends ArrayAdapter {
    Activity activitycontext;
    ArrayList<Clientes> objects;

    public AdaptadorClientes(Activity context,int resource, ArrayList objects) {
        super(context, resource, objects);
        this.activitycontext = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View vista = convertView;
        if (vista == null){
            LayoutInflater inflater = activitycontext.getLayoutInflater();
            vista = inflater.inflate(R.layout.simple_item_listview,null);
            ((TextView)vista.findViewById(R.id.nombre_TextView)).setText(objects.get(position).getNombre());
            ((TextView)vista.findViewById(R.id.apellido_TextView)).setText(objects.get(position).getApellidos());
            ((TextView)vista.findViewById(R.id.dni_TextView)).setText(objects.get(position).getDni());
        }

        return  vista;
    }
}
