package project.post_ident.classes;

import org.junit.Assert;
import org.junit.Test;
import project.post_ident.entities.TempPersonendaten;

import java.io.IOException;

public class BildHochladenLogikTest {


    @Test
    public void felixAusweisAusgabe() throws IOException {
        // 1. Vorbereitung Testdaten
        String pathNameTest ="C:\\Users\\kcoep\\project_postident\\src\\test\\resources\\static\\images\\perso_felix.png";

        // 2. Ausführen der Logik
        BildHochladenLogik bildHochladenLogik = new BildHochladenLogik();
        TempPersonendaten temp =  new TempPersonendaten();
        temp = bildHochladenLogik.bildHochladenLogik(pathNameTest);


        // 3. Prüfen der Ergebnisse
        Assert.assertEquals("IEYER ’",temp.getNachname());
        Assert.assertEquals("FELIX",temp.getVorname());
    }


    @Test
    public void kazimAusweisAusgabe() throws IOException {
        // 1. Vorbereitung Testdaten
        String pathNameTest ="src/test/resources/static/images/PersoKazimGuevenc.jpg";

        // 2. Ausführen der Logik
        BildHochladenLogik bildHochladenLogik = new BildHochladenLogik();
        TempPersonendaten temp =  new TempPersonendaten();
        temp = bildHochladenLogik.bildHochladenLogik(pathNameTest);

        // 3. Prüfen der Ergebnisse
        Assert.assertEquals("GUVENC",temp.getNachname());
        Assert.assertEquals("KAZIM",temp.getVorname());
    }

    @Test
    public void olliAusweisAusgabe() throws IOException {
        // 1. Vorbereitung Testdaten
        String pathNameTest ="src/test/resources/static/images/ausweisOlli.png";

        // 2. Ausführen der Logik
        BildHochladenLogik bildHochladenLogik = new BildHochladenLogik();
        TempPersonendaten temp =  new TempPersonendaten();
        temp = bildHochladenLogik.bildHochladenLogik(pathNameTest);

        // 3. Prüfen der Ergebnisse
        Assert.assertEquals("HOCK",temp.getNachname());
        Assert.assertEquals("0LIVERLUDGER",temp.getVorname());
    }







}
