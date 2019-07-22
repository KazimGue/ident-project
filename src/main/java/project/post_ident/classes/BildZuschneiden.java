package project.post_ident.classes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//Klasse, die das hochgeladene Bild in mehrere Teile zugeschnitten wird,
// die dann von der Tess4j-Klasse ausgelesen werden
public class BildZuschneiden {

        public static void bildZuschneiden() {
            try {
                //BufferedImage originalImage = ImageIO.read(new File("src\\main\\resources\\static\\images\\personalausweisVorneclean.png"));
                BufferedImage originalImage = ImageIO.read(new File("src\\main\\resources\\static\\images\\personalausweisVorneclean.png"));

                //total width and total height of an image
                int tWidth = originalImage.getWidth();
                int tHeight = originalImage.getHeight();

                System.out.println("Image Dimension: " + tWidth + "x" + tHeight);

                //width and height of each piece
                int firstPartWidth = 298;
                int firstPartHeight = 165;

                int firstX = 447;
                int firstY = 70;

                int secondPartWidth = 500;
                int secondPartHeight = 164;

                int secondX =450;
                int secondY = 242;

                try {
                BufferedImage SubImgage = originalImage.getSubimage(firstX, firstY, firstPartWidth, firstPartHeight);
                File outputfileName = new File("src\\main\\resources\\static\\images\\personalausweisVorneName.png");
                ImageIO.write(SubImgage, "png", outputfileName);

                BufferedImage SubImgageTwo = originalImage.getSubimage(secondX, secondY, secondPartWidth, secondPartHeight);
                File outputfileGeburt = new File("src\\main\\resources\\static\\images\\personalausweisVorneGeburtstag.png");
                ImageIO.write(SubImgageTwo, "png", outputfileGeburt);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

