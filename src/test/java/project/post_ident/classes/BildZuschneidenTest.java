package project.post_ident.classes;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BildZuschneidenTest {

    // Testen ob die hochgeladenen Bilder auch wirklich verkleinert werden
    // In vorgegebene Größe 1018x647

    @Test
    public void ausweisFelixBildVerkleinern() throws IOException {
        // 1. Vorbereitung Testdaten
        String pathNameTest = "src/test/resources/static/images/perso_felix.png";

        // 2. Ausführen der Logik
        BildZuschneiden bildZuschneiden = new BildZuschneiden();
        bildZuschneiden.bildZuschneiden(pathNameTest);

        BufferedImage img = ImageIO.read(new File("src\\main\\resources\\static\\images\\Personalausweis_resized-500x500.png"));
        int height = img.getHeight();
        int width = img.getWidth();

        // 3. Prüfen der Ergebenisse
        Assert.assertEquals(647,height);
        Assert.assertEquals(1018,width);
    }


    @Test
    public void ausweisOlliBildVerkleinern() throws IOException {
        // 1. Vorbereitung Testdaten
        String pathNameTest = "src/test/resources/static/images/ausweisOlli.png";

        // 2. Ausführen der Logik
        BildZuschneiden bildZuschneiden = new BildZuschneiden();
        bildZuschneiden.bildZuschneiden(pathNameTest);

        BufferedImage img = ImageIO.read(new File("src\\main\\resources\\static\\images\\Personalausweis_resized-500x500.png"));
        int height = img.getHeight();
        int width = img.getWidth();

        // 3. Prüfen der Ergebenisse
        Assert.assertEquals(647,height);
        Assert.assertEquals(1018,width);
    }


    // Testen ob das Bild in zwei Teile geschnitten wird

    @Test
    public void ausweisFelixBeideTeileExist() throws IOException {
        // 1. Vorbereitung Testdaten
        String pathNameTest = "src/test/resources/static/images/perso_felix.png";

        // 2. Ausführen der Logik
        BildZuschneiden bildZuschneiden = new BildZuschneiden();
        bildZuschneiden.bildZuschneiden(pathNameTest);
        File outputFirstPart = new File("src\\main\\resources\\static\\images\\personalausweisVorneName.png");
        File outputSecondPart = new File("src\\main\\resources\\static\\images\\personalausweisVorneGeburtstag.png");

        // Größen zuschnitt des ersten Parts checken
        BufferedImage img = ImageIO.read(new File("src\\main\\resources\\static\\images\\personalausweisVorneName.png"));
        int x = img.getHeight();
        int y = img.getWidth();

        // 3. Prüfen der Ergebenisse
        Assert.assertTrue(outputFirstPart.canRead());
        Assert.assertTrue(outputSecondPart.canRead());
        Assert.assertEquals(175,x);
        Assert.assertEquals(298,y);
    }

    @Test
    public void ausweisOlliBeideTeileExist() throws IOException {
        // 1. Vorbereitung Testdaten
        String pathNameTest = "src/test/resources/static/images/ausweisOlli.png";

        // 2. Ausführen der Logik
        BildZuschneiden bildZuschneiden = new BildZuschneiden();
        bildZuschneiden.bildZuschneiden(pathNameTest);
        File outputFirstPart = new File("src\\main\\resources\\static\\images\\personalausweisVorneName.png");
        File outputSecondPart = new File("src\\main\\resources\\static\\images\\personalausweisVorneGeburtstag.png");


        BufferedImage img = ImageIO.read(new File("src\\main\\resources\\static\\images\\personalausweisVorneName.png"));
        int x = img.getHeight();
        int y = img.getWidth();


        // 3. Prüfen der Ergebenisse
        Assert.assertTrue(outputFirstPart.canRead());
        Assert.assertTrue(outputSecondPart.canRead());
        Assert.assertEquals(175,x);
        Assert.assertEquals(298,y);
    }


}
