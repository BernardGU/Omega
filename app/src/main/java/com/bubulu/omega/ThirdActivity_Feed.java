package com.bubulu.omega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Entity;

import java.util.ArrayList;

public class ThirdActivity_Feed extends AppCompatActivity {

    FirebaseFirestore db;

    private ListView lvItems;
    private Adaptador adaptador;
    private Button lb_carga;

    public ArrayList<Dog> allItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadDogsFromDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third__feed);
        lb_carga = ( Button ) findViewById(R.id.lb_carga);

        lb_carga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lb_carga.setVisibility(View.INVISIBLE);

                //Load ListView
                adaptador = new Adaptador(ThirdActivity_Feed.this, GetArrayItems());
                lvItems.setAdapter(adaptador);
            }
        });


        lvItems = (ListView) findViewById(R.id.lvlItems);
    }

    private void loadDogsFromDatabase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        allItems = new ArrayList<Dog>();

        db.collection("Dog")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getData().get("id") != null) {
                                    Log.d("Tag", document.getId() + " => " + document.getData());

                                    allItems.add(new Dog(
                                            (long) document.get("id"),
                                            (String) document.get("name"),
                                            (String) document.get("breed"),
                                            (String) document.get("description"),
                                            (String) document.get("imageLink"),
                                            (Boolean) document.get("perdido"),
                                            (Boolean) document.get("encontrado")
                                    ));

                                    //allItems.add(new Dog(document.getData()));
                                    Log.d("After", (new Dog(document.getData())).toString());
                                }

                            }
                        } else {
                            Log.w("Tag", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private ArrayList<Dog> GetArrayItems() {
        Log.w("Tag", "El array es de este tamaño" + String.valueOf(allItems.size()));
        return allItems;
    }
}
