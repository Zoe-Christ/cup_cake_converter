package com.example.cup_cake_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

        // Zutaten vom Spinner als String
       String zutatenstring = spinnerZutaten.getSelectedItem().toString();

        //Eingabe Zutatenmenge als double speichern
      EditText zutatenmenge = (EditText) findViewById(R.id.editTextMenge);
      double mengendouble = 1.0;
      //hier liegt der Fehler:
      mengendouble =Double.parseDouble(zutatenmenge.getEditableText().toString());

        // variable für den passenden Faktor je nach Zutat
       double umrechnungsfaktor = 1;

        // double in dem das Ergebnis gespeichert werden soll
       double ergebnis = 1.0;

        switch(zutatenstring){
            case "Mehl":
                umrechnungsfaktor = 130;
                break;
            case "Puderzucker":
                umrechnungsfaktor = 130;
                break;
            case "Staerke":
                umrechnungsfaktor = 100;
                break;
            case "Zucker":
                umrechnungsfaktor = 200;
                break;
            case "Butter":
                umrechnungsfaktor = 225;
                break;
            case "ganze Nuesse":
                umrechnungsfaktor = 112;
                break;
            case "Backpulver":
                umrechnungsfaktor = 162;
                break;
            case "geriebene Nuesse":
                umrechnungsfaktor = 160;
                break;
            case "Kakaopulver":
                umrechnungsfaktor = 113;
                break;
            case "Oel":
                umrechnungsfaktor = 224;
                break;
            case "Ahornsirup":
                umrechnungsfaktor = 311;
                break;
            case "Chocolate Chips":
                umrechnungsfaktor = 170;
                break;
            case "Haferflocken":
                umrechnungsfaktor = 300;
                break;
            case "Kokosflocken":
                umrechnungsfaktor = 80;
                break;
                default:
               umrechnungsfaktor = 1;
               break;
        }


        ergebnis = mengendouble / umrechnungsfaktor;



        TextView ergebnisFeld = (TextView) findViewById(R.id.textViewErgebnis);
        ergebnisFeld.setText("" + ergebnis);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelected = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, sSelected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
