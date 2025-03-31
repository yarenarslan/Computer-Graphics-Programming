package com.example.lab5_gles_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button torusButton = findViewById(R.id.button_torus);
        Button earthButton = findViewById(R.id.button_earth);
        Button seaviewButton = findViewById(R.id.button_seaview);

        torusButton.setOnClickListener(v -> launchMode("torus"));
        earthButton.setOnClickListener(v -> launchMode("earth"));
        seaviewButton.setOnClickListener(v -> launchMode("seaview"));
    }

    private void launchMode(String mode) {
        Intent intent = new Intent(this, SceneActivity.class);
        intent.putExtra("mode", mode);
        startActivity(intent);
    }
}
