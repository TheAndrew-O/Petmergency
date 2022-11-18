package com.example.petmergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectDogSize extends AppCompatActivity {

    private Button small_dog_but, large_dog_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dog_size);

        small_dog_but = findViewById(R.id.smallDog);
        small_dog_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectDogSize.this, chokingSmall.class);
                    startActivity(intent);
                }
        });

        large_dog_but = findViewById(R.id.largeDog);
        large_dog_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectDogSize.this, chokingLarge.class);
                startActivity(intent);
            }
        });

    }
}