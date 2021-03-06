package com.example.cuc.personasbasededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CUC on 13/05/2017.
 */

public class PersonasSQLiteOpenHelper extends SQLiteOpenHelper{
    private String sql = "CREATE TABLE Personas(foto text,cedula tex,nombre text,apellido,sexo text,pasatiempo)";

    public PersonasSQLiteOpenHelper(Context contexto,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(contexto,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Personas");
        db.execSQL(sql);
    }
}
