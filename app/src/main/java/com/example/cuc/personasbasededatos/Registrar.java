package com.example.cuc.personasbasededatos;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {
    private EditText cajaCedula,cajaNombre,cajaApellido;
    private RadioButton rMasculino,rFemenino;
    private CheckBox chkProgramar,chkLeer,chkBailar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaCedula=(EditText)findViewById(R.id.txtCedula);
        cajaNombre=(EditText)findViewById(R.id.txtNombre);
        cajaApellido=(EditText)findViewById(R.id.txtApellido);
        rMasculino=(RadioButton)findViewById(R.id.r1);
        rFemenino=(RadioButton)findViewById(R.id.r2);
        chkProgramar=(CheckBox)findViewById(R.id.chkProgramar);
        chkLeer=(CheckBox)findViewById(R.id.chkLeer);
        chkBailar=(CheckBox)findViewById(R.id.chkBailar);


    }

    public void guardar(View v){
        String foto,cedula,nombre,apellido,sexo,pasatiempo="";
        Persona p;
        if (validarTodo()){
            cedula=cajaCedula.getText().toString();
            foto= String.valueOf(fotoaleatoria());
            nombre=cajaNombre.getText().toString();
            apellido=cajaApellido.getText().toString();
            if (rMasculino.isChecked()) sexo=getResources().getString(R.string.masculino);
            else sexo=getResources().getString(R.string.femenino);

            if (chkProgramar.isChecked()){
                pasatiempo=getResources().getString(R.string.programar)+", ";
            }
            if (chkLeer.isChecked()){
                pasatiempo=pasatiempo+getResources().getString(R.string.leer)+", ";
            }
            if (chkBailar.isChecked()){
                pasatiempo=pasatiempo+getResources().getString(R.string.bailar)+", " ;
            }
            //Le quita el espacio y la "," al final
            pasatiempo=pasatiempo.substring(0,pasatiempo.length()-2);
            p = new Persona(foto,cedula,nombre,apellido,sexo,pasatiempo);
            p.guardar(getApplicationContext());
            new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.mensaje2)).setCancelable(true).show();
        }
    }

    public boolean validarTodo(){
        if(cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError(getResources().getString(R.string.error1));
            cajaCedula.requestFocus();
            return false;
        }
        if(cajaNombre.getText().toString().isEmpty()){
            cajaNombre.setError(getResources().getString(R.string.error2));
            cajaNombre.requestFocus();
            return false;
        }
        if(cajaApellido.getText().toString().isEmpty()){
            cajaApellido.setError(getResources().getString(R.string.error3));
            cajaApellido.requestFocus();
            return false;
        }

        if ((!chkProgramar.isChecked())&&(!chkLeer.isChecked())&&(!chkBailar.isChecked())){
            new AlertDialog.Builder(getApplicationContext()).setMessage(getResources().getString(R.string.errorpasatiempo)).setCancelable(true).show();
        }
        return true;
    }

    public int fotoaleatoria(){
        int foto[]={R.drawable.images,R.drawable.images2,R.drawable.images3};
        int numero=(int)(Math.random()*3);
        return foto[numero];
    }

    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        rMasculino.setChecked(true);
        chkProgramar.setChecked(false);
        chkLeer.setChecked(false);
        chkBailar.setChecked(false);
        cajaCedula.requestFocus();
    }

    public void borrar(View v){limpiar();}

    public boolean validarCedula(){
        if (cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError(getResources().getString(R.string.error1));
            cajaCedula.requestFocus();
            return false;
        }
        return true;
    }

    public void buscar(View v){
        Persona p;
        if (validarCedula()){
            p=Datos.buscarPersona(getApplicationContext(),cajaCedula.getText().toString());
            if (p!=null){
                cajaNombre.setText(p.getNombre());
                cajaApellido.setText(p.getApellido());

                if (p.getSexo().equalsIgnoreCase(getResources().getString(R.string.masculino)))rMasculino.setChecked(true);
                else rFemenino.setChecked(true);

                if (p.getPasatiempo().contains(getResources().getString(R.string.programar)))chkProgramar.setChecked(true);
                if (p.getPasatiempo().contains(getResources().getString(R.string.leer)))chkLeer.setChecked(true);
                if (p.getPasatiempo().contains(getResources().getString(R.string.bailar)))chkBailar.setChecked(true);
            }
        }

    }

    public void eliminar(View v){
        Persona p;
        if (validarCedula()){
            p=Datos.buscarPersona(getApplicationContext(),cajaCedula.getText().toString());
            if (p!=null){
                AlertDialog.Builder ventana=new AlertDialog.Builder(this);
                ventana.setTitle("Confirmacion");
                ventana.setMessage("¿Esta seguro que desea eliminar esta perona?");
                ventana.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Persona p;
                        p=Datos.buscarPersona(getApplicationContext(),cajaCedula.getText().toString());
                        p.eliminar(getApplicationContext());
                        Toast.makeText(getApplicationContext(),getResources().getString(R.string.mensaje3),
                                Toast.LENGTH_SHORT).show();
                        limpiar();
                    }
                });
                ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cajaCedula.requestFocus();
                    }
                });
                ventana.show();
            }
        }
    }

    public void modificar(View v){
        Persona p;
        if (validarCedula()){
            p=Datos.buscarPersona(getApplicationContext(),cajaCedula.getText().toString());
            if (p!=null){

            }
        }
    }
}
