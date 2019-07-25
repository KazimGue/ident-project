package project.post_ident.classes;

import project.post_ident.entities.TempPersonendaten;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static project.post_ident.classes.Tess4J.getResult;


public class BildHochladenLogik {



    public TempPersonendaten bildHochladenLogik(String pathname) throws IOException {

        OCRResultObject ocrResultObject = getResult(pathname);
        String resultOCR = ocrResultObject.getResult();
        String resultOCR2 = ocrResultObject.getResult2();
        System.out.println(resultOCR+resultOCR2);

        // Namen und Vornamen in einzelne Strings umwandeln
        String vornameString = resultOCR;

        String[] vorname = vornameString.split("\\r?\\n");

        ArrayList<String> daten = new ArrayList<>();

        for(String a : vorname){
            /*System.out.println("vorher " + a);*/
            String[] inhalt = a.split("(?=[a-z])");

            for (String b : inhalt) {
                /* System.out.println("For Schleife " + b);*/
                if (b.length() > 3) {
                    daten.add(b);
                }else {
                    /*System.out.println("Keine Daten länger als 3");*/
                }
            }
        }

        String nachname = "";
        String geborenNamen = "";
        String name = "";

        if(daten.isEmpty()){
            System.out.println("Empty");
        } else if(daten.size() <=1){
            System.out.println("Nichts gefunden außer: " +daten.get(0));
        } else if(daten.size() <= 2){
            nachname = daten.get(0);
            nachname = nachname.replaceAll(" \\s", "");
            /*System.out.println("Nachname nach leerzeichen bei 2: " + nachname);*/

            name = daten.get(1);
            name = name.replaceAll("\\s","");
            System.out.println("Name nach leerzeichen bei 2: " + name);

        } else if(daten.size() <=3){
            nachname = daten.get(0);
            nachname = nachname.replaceAll("\\s","");
            /*System.out.println("Nachname nach leerzeichen bei 3: " + nachname);*/

            geborenNamen = daten.get(1);
            geborenNamen = geborenNamen.replaceAll("\\s", "");
            geborenNamen = geborenNamen.replaceAll("\\(","");
            System.out.println("GeborenName nach leerzeichen bei 3: " + geborenNamen);

            name = daten.get(2);
            name = name.replaceAll("\\s", "");
            System.out.println("Vorname nach leerzeichen bei 3: " + name);
        } else {
            /*System.out.println("Mehr als 3 Daten gefunden deshalb nicht zuordnenbar");*/
        }


        /*
        Bildzuschnitt Nummer 2 auslesen. LOGIK
        Geburtsdatum + Staatsangehörigkeit + Geburtsort auslesen und zuordnen
        */

        // 1. String nach Zeilenumbrüchen aufteilen und in StringArray erstellen
        String[] bildabschnitt2 = resultOCR2.split("\\r?\\n");

        // 1a. Leere Strings löschen
        List<String> datenblock2 = new ArrayList(Arrays.asList(bildabschnitt2));
        datenblock2.removeAll(Arrays.asList("", null));
        String[] bildabschnitt2Gesauebert = new String[datenblock2.size()];
        bildabschnitt2Gesauebert = datenblock2.toArray(bildabschnitt2Gesauebert);






        // ArrayList und Strings für die Iteration  und danach erstellen
//        ArrayList<String> datenblock2 = new ArrayList<>();
        List<String> datenblock3 = new ArrayList<String>(Arrays.asList(bildabschnitt2));
        String geburtsdatumOrt ="";
        String geburtsDatum ="";
        String staatsangehörigkeit ="";
        String geburtsOrtZeile ="";
        String geburtsOrt ="";

        // 2. check ob in der Zeile mehrere Zahlen sind, dann lösch die Leerzeichen (if dann Bedingung)
        for(String a:bildabschnitt2Gesauebert){
            if(a.matches("(?:\\d.*?){4,}")){
                geburtsdatumOrt = a;
                int i = datenblock2.indexOf(a)+2;
                geburtsOrtZeile = datenblock2.get(i);
                datenblock2.remove(geburtsdatumOrt);
                geburtsdatumOrt = a.replaceAll("\\s","");
                System.out.println("GEburtsdatum+ort " + geburtsdatumOrt);
//              datenblock3 = Arrays.asList(bildabschnitt2);
            }
        }

        // + 2a. extrahier die Zahlen in Datum Pattern und
        Pattern pattern2 = Pattern.compile("(\\d{2}.\\d{2}.\\d{4})");
        Matcher m2 = pattern2.matcher(geburtsdatumOrt);
        if (m2.find()) {
            geburtsDatum=m2.group(1);
            System.out.println("Geburtsdatum: " +geburtsDatum);
        }

        // 2b. das Wort mit Großbuchstabe in Staatsangehörigkeit (Pattern)
        Pattern patternAZ = Pattern.compile("[A-Z]+");
        Matcher m3 = patternAZ.matcher(geburtsdatumOrt);
        if (m3.find()) {
            staatsangehörigkeit=m3.group();
            System.out.println("Staatsangehörigkeit: " +staatsangehörigkeit);
        }

        staatsangehörigkeit = staatsangehörigkeit.replaceAll("\\s","");
        geburtsOrt = geburtsOrt.replaceAll("\\s","");
        geburtsOrtZeile = geburtsOrtZeile.replaceAll("\\s","");

        // get GeburtsortZeilen String und großbuchstaben
        Matcher m4 = patternAZ.matcher(geburtsOrtZeile);
        if (m4.find()) {
            geburtsOrt=m4.group();
            System.out.println("Geburtsort: " +geburtsOrt);
        }

        // 4. lösch die Leerzeichen
        staatsangehörigkeit = staatsangehörigkeit.replaceAll("\\s","");
        geburtsOrt = geburtsOrt.replaceAll("\\s","");



        TempPersonendaten gescannteDaten = new TempPersonendaten();
        gescannteDaten.setNachname(nachname);
        gescannteDaten.setVorname(name);
        gescannteDaten.setPersoNr("Personummer");
        gescannteDaten.setGeburtstag(geburtsDatum);
        gescannteDaten.setNationalitaet(staatsangehörigkeit);
        gescannteDaten.setAdresse("HausNr");
        gescannteDaten.setPlz(12345);
        gescannteDaten.setGeburtsort(geburtsOrt);

        return gescannteDaten;

    }



}
