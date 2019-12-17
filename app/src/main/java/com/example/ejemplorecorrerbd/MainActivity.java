package com.example.ejemplorecorrerbd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listaDatos;
    ArrayList<Clientes> datosClientes = new ArrayList<>();
    ArrayList<Clientes> datosClientesConsulltados = new ArrayList<>();

    SQLiteDatabase sqLiteDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relleñarDatosClientes();

        listaDatos = findViewById(R.id.listaDatosListView);

        BDAdapter bdAdapter = new BDAdapter(this,"BD1",1);
        for (Clientes cliente : datosClientes) {
            bdAdapter.insertarUnDato(cliente,sqLiteDatabase);
        }
        Cursor cursor = bdAdapter.consultarDatos();

        datosClientesConsulltados = bdAdapter.getClientes(cursor);


        listaDatos.setAdapter(new AdaptadorClientes(this,R.layout.simple_item_listview,datosClientesConsulltados));
    }

    public void relleñarDatosClientes(){
        Clientes cliente1 = new Clientes("234233Y","pepe","flores");
        Clientes cliente2= new Clientes("234343X","leo","messi");
        Clientes cliente3 = new Clientes("214223B","rodrigo","moreno");
        Clientes cliente4 = new Clientes("128333Y","dani","parejo");
        Clientes cliente5 = new Clientes("197733X","Scarleth","Torres");

        datosClientes.add(cliente1);
        datosClientes.add(cliente2);
        datosClientes.add(cliente3);
        datosClientes.add(cliente4);
        datosClientes.add(cliente5);

    }


}
