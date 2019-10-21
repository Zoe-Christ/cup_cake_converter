package com.example.cup_cake_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerZutaten = (Spinner) findViewById(R.id.spinnerZutaten);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.zutaten_string, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZutaten.setAdapter(adapter);
        spinnerZutaten.setOnItemSelectedListener(this);

        
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelected = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, sSelected, Toast.LENGTH_SHORT.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
