package project.post_ident.classes;

import org.springframework.beans.factory.annotation.Autowired;
import project.post_ident.entities.TempPersonendaten;
import project.post_ident.repository.BildRepository;
import project.post_ident.repository.TempPersonenDatenRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static project.post_ident.classes.Tess4J.getResult;

public class BildHochladenLogik {



    public TempPersonendaten bildHochladenLogik() throws IOException {

        OCRResultObject ocrResultObject = getResult();
        String resultOCR = ocrResultObject.getResult();
        String resultOCR2 = ocrResultObject.getResult2();
        System.out.println(resultOCR+resultOCR2);

        // Namen und Vornamen in einzelne Strings umwandeln
        String vornameString = resultOCR;

        String[] vorname = vornameString.split("\\r?\\n");

        ArrayList<String> daten = new ArrayList<>();

        for(String a : vorname){
            System.out.println("vorher " + a);
            String[] inhalt = a.split("(?=[a-z])");

            for (String b : inhalt) {
                System.out.println("For Schleife " + b);
                if (b.length() > 3) {
                    daten.add(b);
                }else {
                    System.out.println("Keine Daten länger als 3");
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
            System.out.println("Nachname nach leerzeichen bei 2: " + nachname);

            name = daten.get(1);
            name = name.replaceAll("\\s","");
            System.out.println("Name nach leerzeichen bei 2: " + name);

        } else if(daten.size() <=3){
            nachname = daten.get(0);
            nachname = nachname.replaceAll("\\s","");
            System.out.println("Nachname nach leerzeichen bei 3: " + nachname);

            geborenNamen = daten.get(1);
            geborenNamen = geborenNamen.replaceAll("\\s", "");
            geborenNamen = geborenNamen.replaceAll("\\(","");
            System.out.println("GeborenName nach leerzeichen bei 3: " + geborenNamen);

            name = daten.get(2);
            name = name.replaceAll("\\s", "");
            System.out.println("Vorname nach leerzeichen bei 3: " + name);
        } else {
            System.out.println("Mehr als 3 Daten gefunden deshalb nicht zuordnenbar");
        }

        // Datum zuordnen
        String datum = "";
        String sampleDate=resultOCR2.replaceAll("\\s", "");
        Pattern pattern2 = Pattern.compile("(\\d{2}.\\d{2}.\\d{4})");
        Matcher m2 = pattern2.matcher(sampleDate);
        if (m2.find()) {
            datum=m2.group(1);
            System.out.println("Geburtsdatun: " +datum);
        }







        TempPersonendaten gescannteDaten = new TempPersonendaten();
        gescannteDaten.setNachname(nachname);
        gescannteDaten.setVorname(name);
        gescannteDaten.setPersoNr("Personummer");
        gescannteDaten.setGeburtstag(datum);
        gescannteDaten.setStrasse("Straße");
        gescannteDaten.setHausnummer("HausNr");
        gescannteDaten.setPlz(12345);
        gescannteDaten.setStadt("Stadt");

        return gescannteDaten;

    }



}
