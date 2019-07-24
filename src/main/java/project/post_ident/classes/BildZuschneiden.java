package project.post_ident.classes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//Klasse, die das hochgeladene Bild in mehrere Teile zugeschnitten wird,
// die dann von der Tess4j-Klasse ausgelesen werden
public class BildZuschneiden {

    public static void bildZuschneiden() throws IOException {

        try {
            BufferedImage originalImage = ImageIO.read(new File("src\\main\\resources\\static\\images\\persoUpload.png"));

            // Resize originalImage to 1018x647 px um zu schneiden
            BufferedImage resizedImage = resize(originalImage, 647, 1018);

            File output = new File("src\\main\\resources\\static\\images\\Personalausweis_resized-500x500.png");
            ImageIO.write(resizedImage, "png", output);

            BufferedImage resized = ImageIO.read(new File("src\\main\\resources\\static\\images\\Personalausweis_resized-500x500.png"));


//                BufferedImage resized = new BufferedImage();


            //width and height of each piece
            int firstPartWidth = 298;
            int firstPartHeight = 175;

            int firstX = 440;
            int firstY = 75;

            int secondPartWidth = 490;
            int secondPartHeight = 170;

            int secondX = 440;
            int secondY = 270;

            try {
                BufferedImage SubImgage = resized.getSubimage(firstX, firstY, firstPartWidth, firstPartHeight);
                File outputfileName = new File("src\\main\\resources\\static\\images\\personalausweisVorneName.png");
                ImageIO.write(SubImgage, "png", outputfileName);

                BufferedImage SubImgageTwo = resized.getSubimage(secondX, secondY, secondPartWidth, secondPartHeight);
                File outputfileGeburt = new File("src\\main\\resources\\static\\images\\personalausweisVorneGeburtstag.png");
                ImageIO.write(SubImgageTwo, "png", outputfileGeburt);


            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();


        return resized;
    }
}

