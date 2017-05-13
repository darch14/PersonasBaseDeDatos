package com.example.cuc.personasbasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by CUC on 12/05/2017.
 */

public class Persona {
    private String foto;
    private String cedula;
    private String nombre;
    private String apellido;
    private String sexo;
    private String pasatiempo;

    public Persona(String foto, String cedula, String nombre, String apellido, String sexo, String pasatiempo) {
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.pasatiempo = pasatiempo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPasatiempo() {
        return pasatiempo;
    }

    public void setPasatiempo(String pasatiempo) {
        this.pasatiempo = pasatiempo;
    }

    public void guardar(Context conexto){
        //declarar las variables
        String sql;
        SQLiteDatabase db;

        //Abrir ña conexión de base de datos en modo escritura
        PersonasSQLiteOpenHelper  aux=new PersonasSQLiteOpenHelper(conexto,"DBPersonas",null,1);
        db=aux.getWritableDatabase();

        //insertar version 1

        sql="INSERT INTO Personas values('"+this.getFoto()+"','"+this.getCedula()+"','"+
                this.getNombre()+"','"+this.getApellido()+"','"+this.getSexo()+"','"+
                this.getPasatiempo()+"')";
        db.execSQL(sql);

        //forma 2
        /*ContentValues nuevoRegistro=new ContentValues();
        nuevoRegistro.put("foto",this.getFoto());
        nuevoRegistro.put("cedula",this.getCedula());
        nuevoRegistro.put("nombre",this.getNombre());
        nuevoRegistro.put("apellido",this.getSexo());
        nuevoRegistro.put("sexo",this.getSexo());
        nuevoRegistro.put("pasatiempo",this.getPasatiempo());

        db.insert("Personas",null,nuevoRegistro);*/

        //Cerrar conexión
        db.close();
    }

    public void eliminar(Context conexto){
        //declarar las variables
        String sql;
        SQLiteDatabase db;

        //Abrir ña conexión de base de datos en modo escritura
        PersonasSQLiteOpenHelper  aux=new PersonasSQLiteOpenHelper(conexto,"DBPersonas",null,1);
        db=aux.getWritableDatabase();

        sql="DELETE FROM Personas where cedula ='"+this.getCedula()+"'";
        db.execSQL(sql);


        //Cerrar conexión
        db.close();
    }

    public void modificar(Context conexto){
        //declarar las variables
        String sql;
        SQLiteDatabase db;

        //Abrir ña conexión de base de datos en modo escritura
        PersonasSQLiteOpenHelper  aux=new PersonasSQLiteOpenHelper(conexto,"DBPersonas",null,1);
        db=aux.getWritableDatabase();

        //insertar version 1

        sql="UPDATE Personas set nombre='"+this.getNombre()+
                "',apellido ='"+this.getApellido()+
                "',sexo ='"+this.getSexo()
                +"',pasatiempo ='"+this.getPasatiempo()+
                "where cedula ='"+this.getCedula()+"'";
        db.execSQL(sql);

        //forma 2
        /*ContentValues nuevoRegistro=new ContentValues();
        nuevoRegistro.put("foto",this.getFoto());
        nuevoRegistro.put("cedula",this.getCedula());
        nuevoRegistro.put("nombre",this.getNombre());
        nuevoRegistro.put("apellido",this.getSexo());
        nuevoRegistro.put("sexo",this.getSexo());
        nuevoRegistro.put("pasatiempo",this.getPasatiempo());

        db.insert("Personas",null,nuevoRegistro);*/

        //Cerrar conexión
        db.close();
    }
}
