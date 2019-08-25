package com.bubulu.omega;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ThirdActivity_Feed extends AppCompatActivity {

    FirebaseFirestore db;

    private ListView lvItems;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third__feed);

        lvItems = (ListView) findViewById(R.id.lvlItems);
        adaptador = new Adaptador(this,GetArrayItems());
        lvItems.setAdapter(adaptador);
    }

    private ArrayList<Entidad> GetArrayItems() {
        ArrayList<Entidad> listItems = new ArrayList<>();

        db = FirebaseFirestore.getInstance();



        listItems.add(new Entidad(R.drawable.omegalogo, "TITULO 1", "CONTENIDO 1"));
        listItems.add(new Entidad(R.drawable.omegalogo, "TITULO 2", "CONTENIDO 2"));
        listItems.add(new Entidad(R.drawable.omegalogo, "TITULO 3", "CONTENIDO 3"));
        listItems.add(new Entidad(R.drawable.omegalogo, "TITULO 4", "CONTENIDO 4"));
        listItems.add(new Entidad(R.drawable.omegalogo, "TITULO 5", "CONTENIDO 5"));

        return listItems;
    }
}
