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
    }

    public Dog(Map<String, Object> readDog) {
        this.id = (int) readDog.get("id");
        this.name = (String) readDog.get("name");
        this.breed = (String) readDog.get("breed");
        this.description = (String) readDog.get("description");
        this.imageLink = (String) readDog.get("imageLink");
    }

    public String getBreed() {return breed;}
    public String getDescription() {return description;}
    public String getName() {return name;}
    public String getImageLink() {return imageLink;}

    public void setName(String name) {this.name = name;}
    public void setBreed(String breed) {this.breed = breed;}
    public void setDescription(String description) {this.description = description;}
    public void setImageLink(String imageLink) {this.imageLink = imageLink;}

    public Dog readFromDatabase(FirebaseFirestore db, int id) {
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

    private int generateId(FirebaseFirestore db) {
        DocumentReference docRef = db.collection("Dog").document("Values");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        iAux = (int) document.get("nextID");
                    } else {
                        Log.d("Dog", "Couldn't find new ID");
                    }
                } else {
                    Log.d("Dog", "generateNewID failed with ", task.getException());
                }
            }
        });

        return iAux;
    }
    public void writeToDatabase(FirebaseFirestore db) {
        Map<String, Object> d = new HashMap<>();
        d.put("id", (this.id != -1)? this.id : generateId(db));
        d.put("name", this.name);
        d.put("breed", this.breed);
        d.put("description", this.description);
        d.put("imageLink", this.imageLink);

        db.collection("Dog")
                .document(String.valueOf(id))
                .set(d);
    }

    private int id;
    private String name;
    private String breed;
    private String description;
    private String imageLink;

    public Dog dogAux;
    public int iAux;
}