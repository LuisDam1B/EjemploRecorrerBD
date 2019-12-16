package com.example.ejemplorecorrerbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.invoke.CallSite;
import java.util.ArrayList;

public class BDAdapter {

    SQLiteDatabase sqLiteDatabase;
    BDClientes bdClientes;
    Context context;

    public BDAdapter(Context context,String nombreBD,int version) {
        this.context = context;
        bdClientes = new BDClientes(context,nombreBD,null,version);

    }

    public void insertarDatos(){
        SQLiteDatabase sqLiteDatabase = bdClientes.getWritableDatabase();

        for (int i = 0 ; i<10;i++)
        {
            String sentencia="INSERT INTO Clientes (dni, nombre, apellidos) VALUES" +
                    " ('"+i+"','nombre"+i+"','apellido"+i+"');";
            sqLiteDatabase.execSQL(sentencia);

        }
        sqLiteDatabase.close();
    }

    public void InsertarUnDato(Clientes cliente){

        SQLiteDatabase sqLiteDatabase = bdClientes.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("dni",cliente.getDni());
        contentValues.put("nombre",cliente.getNombre());
        contentValues.put("apellidos",cliente.getApellidos());

        sqLiteDatabase.insert("Clientes",null,contentValues);
        sqLiteDatabase.close();

    }

    public Cursor consultarDatos()
    {
        sqLiteDatabase = bdClientes.getReadableDatabase();
        if(sqLiteDatabase!=null) {

            return sqLiteDatabase.rawQuery("select  dni, nombre, apellidos from Clientes", null);
        }
        return null;
    }

    public void cerrarBD()
    {
        bdClientes.close();
    }

    public static ArrayList<Clientes> getClientes(Cursor cursor){

        ArrayList<Clientes> clientes;
        Clientes cliente;

        cursor.moveToFirst();//ponemos el cursor en el principio
        if ( !cursor.isAfterLast()){
            clientes = new ArrayList<Clientes>();
            while (!cursor.isAfterLast()){
                cliente = new Clientes(cursor.getString(0),cursor.getString(1),cursor.getString(3));
                clientes.add(cliente);
                cursor.moveToNext();
            }
            return clientes;
        }
        return null;
    }

}
