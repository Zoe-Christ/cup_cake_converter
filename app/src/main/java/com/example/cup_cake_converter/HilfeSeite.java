package com.example.cup_cake_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HilfeSeite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilfe_seite);

        //Button um zurück zur vorherigen Seite zu gelangen
        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
            }
        });

        //Text lässt sich nicht verändern
        EditText hilfeMultiLine = (EditText) findViewById(R.id.hilfeMultiText);
        hilfeMultiLine.setKeyListener(null);
    }
}
