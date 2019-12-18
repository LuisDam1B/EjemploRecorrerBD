package com.example.ejemplorecorrerbd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listaDatos;
    ArrayList<Clientes> datosClientes = new ArrayList<>();
    ArrayList<Clientes> datosClientesConsulltados = new ArrayList<>();

    SQLiteDatabase sqLiteDatabase;
    //Controles de la apliacion
    ImageButton insertarRegristro_Button,mostrarListView_ImageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relleñarDatosClientes();

        listaDatos = findViewById(R.id.listaDatosListView);
        insertarRegristro_Button = findViewById(R.id.addButton);
        mostrarListView_ImageButton = findViewById(R.id.mostrarElementos);

        final BDAdapter bdAdapter = new BDAdapter(this,"BD1",1);
        for (Clientes cliente : datosClientes) {
            bdAdapter.insertarUnDato(cliente,sqLiteDatabase);
        }


        //logica de botones

        insertarRegristro_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextDni = findViewById(R.id.editText_dni);
                EditText editTextNombre = findViewById(R.id.editText_nombre);
                EditText editTextApellido = findViewById(R.id.editText_apellidos);
                if (editTextDni.getText()!=null && editTextNombre.getText()!=null && editTextApellido.getText()!=null){
                    Clientes cliente = new Clientes();
                    cliente.setDni(editTextDni.getText().toString());
                    cliente.setNombre(editTextNombre.getText().toString());
                    cliente.setApellidos(editTextApellido.getText().toString());

                    bdAdapter.insertarUnDato(cliente,sqLiteDatabase);

                }
            }
        });

        mostrarListView_ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = bdAdapter.consultarDatos();

                datosClientesConsulltados = bdAdapter.getClientes(cursor);
                listaDatos.setAdapter(new AdaptadorClientes(MainActivity.this,R.layout.simple_item_listview,datosClientesConsulltados));
            }
        });

        bdAdapter.cerrarBD();
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
