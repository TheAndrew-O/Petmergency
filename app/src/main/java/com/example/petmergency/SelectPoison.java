package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectPoison extends AppCompatActivity {

    private Button inside_but, outside_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_poison);

        outside_but = findViewById(R.id.PoisonConsumed);
        outside_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPoison.this, PoisonConsume.class);
                startActivity(intent);
            }
        });

        inside_but = findViewById(R.id.PoisonExposed);
        inside_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPoison.this, PoisonExpose.class);
                startActivity(intent);
            }
        });

    }
}