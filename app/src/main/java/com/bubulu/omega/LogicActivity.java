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

        @Override
        public void onStart() {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            moveToMain();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(eTxtEmail.getText().toString(), eTxtPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        });
                moveToMain();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(eTxtEmail.getText().toString(), eTxtPassword.getText().toString()) {

                }
            }
            moveToMain();
        });
    }



    private void moveToMain() {
        FirebaseUser user = mAuth.getCurrentUser();
        Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LogicActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
