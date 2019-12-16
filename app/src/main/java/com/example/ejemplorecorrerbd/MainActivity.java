package com.example.ejemplorecorrerbd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listaDatos;
    D

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDatos = findViewById(R.id.listaDatosListView);

        BDAdapter bdAdapter = new BDAdapter(this,"BD1",1);
        bdAdapter.insertarDatos();
        Cursor cursor = bdAdapter.consultarDatos();
    }


}
