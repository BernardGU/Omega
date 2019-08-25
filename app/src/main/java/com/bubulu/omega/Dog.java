package com.bubulu.omega;

import android.util.Log;
import android.view.ViewDebug;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Dog {


    public Dog() {
        this.id = -1;
        this.name = "";
        this.breed = "";
        this.description = "";
        this.imageLink = "";
        this.perdido = false;
        this.encontrado = false;
    }

    public Dog(Map<String, Object> readDog) {
        this.id = (long) readDog.get("id");
        this.name = (String) readDog.get("name");
        this.breed = (String) readDog.get("breed");
        this.description = (String) readDog.get("description");
        this.imageLink = (String) readDog.get("imageLink");
        this.perdido = (Boolean) readDog.get("perdido");
        this.encontrado = (Boolean) readDog.get("encontrado");
    }

    public String getBreed() {return breed;}
    public String getDescription() {return description;}
    public String getName() {return name;}
    public String getImageLink() {return imageLink;}
    public Boolean getEncontrado() {return encontrado;}
    public Boolean getPerdido() {return perdido;}

    public void setName(String name) {this.name = name;}
    public void setBreed(String breed) {this.breed = breed;}
    public void setDescription(String description) {this.description = description;}
    public void setImageLink(String imageLink) {this.imageLink = imageLink;}
    public void setEncontrado(Boolean encontrado) {this.encontrado = encontrado;}
    public void setPerdido(Boolean perdido) {this.perdido = perdido;}

    public Dog readFromDatabase(FirebaseFirestore db, long id) {
        DocumentReference docRef = db.collection("Dog").document(String.valueOf(id));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        dogAux = new Dog(document.getData());
                    } else {
                        Log.d("Tag", "No such document");
                    }
                } else {
                    Log.d("Tag", "get failed with ", task.getException());
                }
            }
        });

        return dogAux;
    }

    public static void generateId(FirebaseFirestore db) {
        DocumentReference docRef = db.collection("Dog").document("Values");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> d = document.getData();
                        lAux = (long) d.get("nextID");
                    } else {
                        Log.d("Dog", "Couldn't find new ID");
                    }
                } else {
                    Log.d("Dog", "generateNewID failed with ", task.getException());
                }
            }
        });
    }

    private static void updateID(FirebaseFirestore db) {
        Map<String, Object> d = new HashMap<>();
        d.put("nextID", lAux + 1);

        db.collection("Dog")
                .document("Values")
                .set(d);
    }

    public void writeToDatabase(FirebaseFirestore db) {
        if(this.id == -1) {
            this.id = lAux;
            updateID(db);
        }

        Map<String, Object> d = new HashMap<>();
        d.put("id", String.valueOf(this.id));
        d.put("name", this.name);
        d.put("breed", this.breed);
        d.put("description", this.description);
        d.put("imageLink", this.imageLink);

        db.collection("Dog")
                .document(String.valueOf(id))
                .set(d);
    }

    private long id;
    private String name;
    private String breed;
    private String description;
    private String imageLink;
    private Boolean perdido;
    private Boolean encontrado;

    public static Dog dogAux;
    public static long lAux;
}