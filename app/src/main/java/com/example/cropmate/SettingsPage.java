package com.example.cropmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageButton homeButton = findViewById(R.id.homeButton); // Ensure you have an ImageButton with this ID in your activity_settings.xml
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(SettingsPage.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
