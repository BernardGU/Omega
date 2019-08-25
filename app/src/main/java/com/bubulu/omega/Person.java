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

public class Person {

    private long id;
    private String name;
    private long contact;
    private long[] dogs;

    public Person() {
        this.id = -1;
        this.name = "";
        this.contact = -1;
        this.dogs[0]= -1;
    }

    public Person(Map<String, Object> readPerson) {
        this.id = (long) readPerson.get("id");
        this.name = (String) readPerson.get("name");
        this.contact = (long) readPerson.get("contact");


    }
}
