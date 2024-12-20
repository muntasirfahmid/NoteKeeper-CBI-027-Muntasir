package com.example.cbi27muntasir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button crudButton, imageButton, sensorButton, locationButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crudButton = findViewById(R.id.buttonCRUD);
        imageButton = findViewById(R.id.buttonImage);
        sensorButton = findViewById(R.id.buttonSensor);
        locationButton = findViewById(R.id.buttonLocation);
        logoutButton = findViewById(R.id.buttonLogout);

        crudButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, NotesActivity.class)));
        imageButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ImageActivity.class)));
        sensorButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SensorActivity.class)));
        locationButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LocationActivity.class)));

        logoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }
}