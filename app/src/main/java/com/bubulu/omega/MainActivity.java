package com.bubulu.omega;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button LB_Reportar;
    private Button LB_feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LB_Reportar = (Button) findViewById(R.id.LB_Reportar);
        LB_feed = (Button) findViewById(R.id.LB_feed);

        //Boton Reportar
        LB_Reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                moveToReportar();
            }

        });

        //Boton Feed
        LB_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                moveToFeed();
            }

        });
    }

    public void onBackPressed() {

    }


    //Funcion void a reportar
    private void moveToReportar()
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity_Report.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    //Funcion void a feed
    private void moveToFeed()
    {
        Intent intent = new Intent(MainActivity.this, ThirdActivity_Feed.class);
        startActivity(intent);
    }
}
