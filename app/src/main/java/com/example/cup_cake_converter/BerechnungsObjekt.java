package com.example.cup_cake_converter;

//Klasse für die Erstellung von Objekten bestehend aus
public class BerechnungsObjekt implements Comparable<BerechnungsObjekt>{
    int id;
    String zutat;
    double menge;
    double ergebnis;

    public BerechnungsObjekt(int i, String z, double m, double e) {
        id =i;
        zutat = z;
        menge = m;
        ergebnis =e;
    }

    @Override
    public int compareTo(BerechnungsObjekt berechnung) {
        int compareId = ((BerechnungsObjekt)berechnung).id;
        return compareId - this.id;
    }
}
