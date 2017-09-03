package com.example.matej.constructionlog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends AppCompatActivity {
    public Button btnPodatci;
    public Button btnLokacije;
    public Button btnKamera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initDatabaseActivity();
        initLocationActivity();
        initCameraActivity();
    }

    private void initCameraActivity() {
        btnKamera = (Button)findViewById(R.id.btnKamera);
        btnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraEditor = new Intent(LauncherActivity.this, CameraActivity.class);
                startActivity(cameraEditor);
            }
        });

    }

    public void initDatabaseActivity(){
        btnPodatci = (Button)findViewById(R.id.btnPodatci);
        btnPodatci.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent dbEditor = new Intent(LauncherActivity.this, DatabaseActivity.class);
                        startActivity(dbEditor);
                    }
                }
        );
    }
    public void initLocationActivity(){
        btnLokacije = (Button)findViewById(R.id.btnLokacije);
        btnLokacije.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent locationEditor = new Intent(LauncherActivity.this, LocationActivity.class);
                        startActivity(locationEditor);
                    }
                }
        );
    }




}
