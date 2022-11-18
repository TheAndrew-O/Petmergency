package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BleedingLocation extends AppCompatActivity {

    private Button torso_but, head_but, internal_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bleeding_location);

        torso_but = findViewById(R.id.torso_bleeding);
        torso_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BleedingLocation.this, bleedingTorso.class);
                startActivity(intent);

            }
        });

        head_but = findViewById(R.id.head_bleeding);
        head_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BleedingLocation.this, bleedingHead.class);
                startActivity(intent);

            }
        });

        internal_but = findViewById(R.id.internal_bleeding);
        internal_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BleedingLocation.this, bleedingInternal.class);
                startActivity(intent);

            }
        });
    }
}