package com.example.cup_cake_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    HistoryDatabase historyDatabase;

    // double in dem das Ergebnis der Berechnung gespeichert werden soll
    double ergebnis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Datenbank zur Speicherung der Bercehnungen
        historyDatabase = new HistoryDatabase(this);

        // Dropdownliste mit auswählbaren Zutaten
        Spinner spinnerZutaten = (Spinner) findViewById(R.id.spinnerZutaten);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.zutaten_string, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZutaten.setAdapter(adapter);
        spinnerZutaten.setOnItemSelectedListener(this);



        //Button zum Start der Konvertierung
        Button rechner = (Button) findViewById(R.id.buttonConverter);
        rechner.setOnClickListener((v) -> {

        // Zutaten vom Spinner als String
       String zutatenstring = spinnerZutaten.getSelectedItem().toString();

       //Eingabe Zutatenmenge als double speichern
            EditText zutatenmenge = (EditText) findViewById(R.id.editTextMenge);

            if(zutatenmenge.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Bitte Menge eingeben", Toast.LENGTH_SHORT).show();
            } else {

                double mengendouble = 1.0;
                mengendouble = Double.parseDouble(zutatenmenge.getText().toString());


                // variable für den passenden Faktor je nach Zutat
                double umrechnungsfaktor = 1;


                // je nach Zutate einen anderen Umrechnungsfaktor wählen
                switch (zutatenstring) {
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
                    case "Honig":
                        umrechnungsfaktor = 340;
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

                //Ergebnis auf zwei Nachkommastellen runden
                ergebnis = (Math.round((mengendouble / umrechnungsfaktor) * 100.0)) / 100.0;

                AddData(zutatenstring, mengendouble, ergebnis);

                //als Ergebnis anzuzeigenden String erstellen
                String s = "" + mengendouble + " g " + zutatenstring + " entsprechen " + ergebnis + " cups";

                // Intent mit Ergebnisstring
                Intent mengenIntent = new Intent(this, Ergebnis.class);
                mengenIntent.putExtra("menge", s);
                startActivity(mengenIntent);

                //Inhalt Textfeld leeren
                zutatenmenge.setText("");

            }

        });

        // Button um zur Hilfeseite zu gelangen
        Button hilfeImageBtn = (Button) findViewById(R.id.buttonhelp);
        hilfeImageBtn.setOnClickListener((v) -> {
            Intent zuHilfe = new Intent(getApplicationContext(), HilfeSeite.class);
            startActivity(zuHilfe);
        });

        //Button um zur Historie zu gelangen
        Button historyButton = (Button) findViewById(R.id.historyBtn);
        historyButton.setOnClickListener((v) -> {
            Intent goToHistory = new Intent(getApplicationContext(), History.class);
            startActivity(goToHistory);
        });

    }



    public void AddData(String zutat, double menge, double ergebnis) {
        boolean addedData = historyDatabase.addData(zutat, menge, ergebnis);
        if(addedData == true)
            Log.i("HistoryDatabase", "AddData: successful");
        else
            Log.i("HistoryDatabase", "AddData: not successful");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelected = parent.getItemAtPosition(position).toString();
            }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
