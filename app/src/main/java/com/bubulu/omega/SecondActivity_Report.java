package com.bubulu.omega;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity_Report extends AppCompatActivity {

    private EditText txt_Nombre;
    private EditText txt_raza;
    private EditText txt_desc;
    private Button LB_encontrado;
    private Button LB_perdido;
    private String st_nombre = "";
    private String st_raza = "";
    private String st_desc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__report);

        txt_Nombre = ( EditText ) findViewById(R.id.txt_Nombre);
        txt_raza = ( EditText ) findViewById(R.id.txt_raza);
        txt_desc = ( EditText ) findViewById(R.id.txt_desc);
        LB_encontrado = ( Button ) findViewById(R.id.LB_encontrado);
        LB_perdido = ( Button ) findViewById(R.id.LB_perdido);

        txt_Nombre.setText(st_nombre);
        txt_raza.setText(st_raza);
        txt_desc.setText(st_desc);

        //st_nombre = txt_Nombre.getText().toString();
        //st_raza = txt_raza.getText().toString();
        //st_desc = txt_desx

        // Boton Guardar
        LB_encontrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                moveToMain();
            }
        });

        LB_perdido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                moveToMain();
            }
        });

    }



    private void moveToMain()
    {
        Intent intent = new Intent(SecondActivity_Report.this, MainActivity.class);
        startActivity(intent);
    }
}
