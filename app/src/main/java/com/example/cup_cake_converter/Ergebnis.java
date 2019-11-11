package com.example.cup_cake_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ergebnis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergebnis);

        //Zurückbutton
        Button backBtn = (Button) findViewById(R.id.buttonErgebnisN);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
            }
        });

        // Intent abrufen
         double d = getIntent().getExtras().getDouble("wert");

        // Edittext Ergebnis erstellen
         EditText ergebnis = (EditText) findViewById(R.id.editTextErgebnis);

       // Intent Ergebnisstring abrufen
        String c = getIntent().getExtras().getString("menge");

        // Ergebnis anzeigen
        ergebnis.setText(c);


    }
}
