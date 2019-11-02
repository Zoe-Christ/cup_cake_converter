package com.example.cup_cake_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Ergebnis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ergebnis);

        //Hier deine TextView initialisieren mit findViewById und so
         Double d = getIntent().getExtras().getDouble("wert");
        /*Toast toast;
         toast = Toast.makeText(this, ""+d, Toast.LENGTH_SHORT);
        toast.show(); */

        TextView ergebnisView = (TextView) findViewById(R.id.textViewE);
        ergebnisView.setText("" + d);


    }
}
