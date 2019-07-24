package project.post_ident.classes;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BildHochladenLogikTest {

    @Test
    public void vierZeilenAusgabe(){

        //Vorbereitung Testdaten
        String[] vierZeilenArray ={"MEHRALSDREI","MEHRALSDREI","MEHRALSDREI","MEHRALSDREI"};

        //Ausführen der Logik
        BildHochladenLogik bildHochladenLogik=new BildHochladenLogik();
        ArrayList<String> outputSplitInputString=bildHochladenLogik.splitInputString(vierZeilenArray);


        //Prüfen der Ergebnisse
        Assert.assertEquals(4,outputSplitInputString.size());
    }


    @Test
    public void dreiZeilenAusgabe(){

        //Vorbereitung Testdaten
        String[] vierZeilenArray ={"MEHRALSDREI","ichbinklein","MEHRALSDREI","MEHRALSDREI"};

        //Ausführen der Logik
        BildHochladenLogik bildHochladenLogik=new BildHochladenLogik();
        ArrayList<String> outputSplitInputString=bildHochladenLogik.splitInputString(vierZeilenArray);


        //Prüfen der Ergebnisse
        Assert.assertEquals(3,outputSplitInputString.size());
    }
}
