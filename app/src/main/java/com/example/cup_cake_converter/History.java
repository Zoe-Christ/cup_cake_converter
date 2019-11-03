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

public class History extends AppCompatActivity {
    TableLayout tableLayout;
    HistoryDatabase historyDatabase;

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
        // historyDatabase.clearDatabase();
        fillTable();
    }

    public void fillTable() {
        Cursor data = historyDatabase.getAllData();
        ArrayList<BerechnungsObjekt> berechnungsListe = new ArrayList<BerechnungsObjekt>();

        while (data.moveToNext()) {
            Integer id = data.getInt(0);
            String zutat = data.getString(1);
            double menge = data.getDouble(2);
            double ergebnis = data.getDouble(3);

            BerechnungsObjekt berechnungsObjekt = new BerechnungsObjekt(id, zutat, menge, ergebnis);

            berechnungsListe.add(berechnungsObjekt);
        }

        Context context = getApplicationContext();

        for(int i=0; i<berechnungsListe.size(); i++) {
            // Create a new table row.
            // TableRow tableRow = new TableRow(context);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableRow tableRow = (TableRow) inflater.inflate(R.layout.history_tablerow, null);

            // Add zutatenTextView
            TextView zutatenTextView = (TextView) tableRow.findViewById(R.id.tv1);
            zutatenTextView.setText(berechnungsListe.get(i).zutat);

            // Add mengenTextView
            TextView mengenTextView = (TextView) tableRow.findViewById(R.id.tv2);
            mengenTextView.setText("" + berechnungsListe.get(i).menge);

            // Add ergebnisTextView
            TextView ergebnisTextView = (TextView) tableRow.findViewById(R.id.tv3);
            ergebnisTextView.setText("" + berechnungsListe.get(i).ergebnis);

            tableRow.setBackgroundColor(getResources().getColor(R.color.white));

            tableLayout.addView(tableRow);
        }



        // Set new table row layout parameters.
        // TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        // tableRow.setLayoutParams(layoutParams);



    }
}
