package com.example.cup_cake_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class History extends AppCompatActivity {
    TableLayout tableLayout;
    HistoryDatabase historyDatabase;
    ArrayList<BerechnungsObjekt> berechnungsListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button backFromHisBtn = (Button) findViewById(R.id.backFromHisBtn);
        backFromHisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
            }
        });

        tableLayout = (TableLayout) findViewById(R.id.historyTable);
        historyDatabase = new HistoryDatabase(this);
        fillTable();

        // Referenz auf den Button zur Leerung der Datenbank
        Button clearDataBtn = (Button) findViewById(R.id.clearDatabaseBtn);
        clearDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDatabase();
                finish();
                startActivity(getIntent());
            }
        });
    }

    // Befüllen der Tabelle mit den Daten aus der Datenbank
    public void fillTable() {
        Cursor data = historyDatabase.getAllData();
        berechnungsListe = new ArrayList<BerechnungsObjekt>();

        while (data.moveToNext()) {
            Integer id = data.getInt(0);
            String zutat = data.getString(1);
            double menge = data.getDouble(2);
            double ergebnis = data.getDouble(3);

            BerechnungsObjekt berechnungsObjekt = new BerechnungsObjekt(id, zutat, menge, ergebnis);

            berechnungsListe.add(berechnungsObjekt);
        }
        Collections.sort(berechnungsListe);
        Context context = getApplicationContext();

        for(int i=0; i<berechnungsListe.size(); i++) {
            // neue Tabellenreihe
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableRow tableRow = (TableRow) inflater.inflate(R.layout.history_tablerow, null);

            // zutatenTextView
            TextView zutatenTextView = (TextView) tableRow.findViewById(R.id.tv1);
            zutatenTextView.setText(berechnungsListe.get(i).zutat);

            // Add mengenTextView
            TextView mengenTextView = (TextView) tableRow.findViewById(R.id.tv2);
            mengenTextView.setText("" + berechnungsListe.get(i).menge);

            // ergebnisTextView
            TextView ergebnisTextView = (TextView) tableRow.findViewById(R.id.tv3);
            ergebnisTextView.setText("" + berechnungsListe.get(i).ergebnis);

            tableLayout.addView(tableRow);
        }

    }

    //Methode zur Leerung der Datenbank
    public void clearDatabase() {
        historyDatabase.clearDatabase();
    }


}
