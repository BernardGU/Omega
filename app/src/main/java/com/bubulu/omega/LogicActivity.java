package com.bubulu.omega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogicActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnLogin;
    private EditText eTxtEmail;
    private EditText eTxtPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        eTxtEmail = (EditText) findViewById(R.id.eTxtEmail);
        eTxtPassword = (EditText) findViewById(R.id.eTxtPassword);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMain();
            }
        });
    }

    private void moveToMain() {
        Intent intent = new Intent(LogicActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
