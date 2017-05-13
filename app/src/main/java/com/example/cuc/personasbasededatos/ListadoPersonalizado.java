package com.example.cuc.personasbasededatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoPersonalizado extends AppCompatActivity {
    private ListView ls;
    private ArrayList<Persona> personas;
    private AdaptadorPersona adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_personalizado);

        ls=(ListView)findViewById(R.id.lvListaPersonalisada);
        personas=Datos.traerPersonas(getApplicationContext());
        adapter=new AdaptadorPersona(getApplicationContext(),personas);
        ls.setAdapter(adapter);
    }
}
