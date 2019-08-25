package com.bubulu.omega;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.firestore.FirebaseFirestore;

public class SecondActivity_Report extends AppCompatActivity {

    FirebaseFirestore db;


    private final int PICK_IMAGE_REQUEST = 71;

    private EditText txt_Nombre;
    private EditText txt_raza;
    private EditText txt_desc;
    private ImageButton LI_upload;
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
        LI_upload = (ImageButton) findViewById(R.id.li_upload);

        txt_Nombre.setText(st_nombre);
        txt_raza.setText(st_raza);
        txt_desc.setText(st_desc);

        //st_nombre = txt_Nombre.getText().toString();
        //st_raza = txt_raza.getText().toString();
        //st_desc =

        db = FirebaseFirestore.getInstance();
        Dog.generateId(db);

        // Boton Guardar
        LB_encontrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dog a = new Dog();
                    a.setName(txt_Nombre.getText().toString());
                    a.setBreed(txt_raza.getText().toString());
                    a.setDescription(txt_desc.getText().toString());
                    a.setEncontrado(true);
                    a.setPerdido(false);
                    a.writeToDatabase(db);

                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity_Report.this);
                builder.setTitle("Éxito");
                builder.setMessage("Perro añadido exitosamente");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        moveToMain();
                    }
                });
                builder.show();
            }
        });

        LB_perdido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dog a = new Dog();
                a.setName(txt_Nombre.getText().toString());
                a.setBreed(txt_raza.getText().toString());
                a.setDescription(txt_desc.getText().toString());
                a.setEncontrado(false);
                a.setPerdido(true);
                a.writeToDatabase(db);
                    //WEDIDITBOYS
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity_Report.this);
                builder.setTitle("Éxito");
                builder.setMessage("Espero lo encontremos pronto");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        moveToMain();
                    }
                });
                builder.show();


            }
        });

        LI_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });




    }



    private void moveToMain()
    {
        Intent intent = new Intent(SecondActivity_Report.this, MainActivity.class);
        startActivity(intent);
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

}

