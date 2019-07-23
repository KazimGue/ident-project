package project.post_ident.classes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//Klasse, die das hochgeladene Bild in mehrere Teile zugeschnitten wird,
// die dann von der Tess4j-Klasse ausgelesen werden
public class BildZuschneiden {

        public static void bildZuschneiden() throws IOException {

            try {
            BufferedImage originalImage = ImageIO.read(new File("src\\main\\resources\\static\\images\\personalausweisVorneclean.png"));
            //BufferedImage originalImage = ImageIO.read(new File("src\\main\\resources\\static\\images\\ausweisOlli.png"));


            try {

                BufferedImage cropOne = originalImage.getSubimage(290,70,460,190);
                File outputfileName = new File("src\\main\\resources\\static\\images\\personalausweisVorneName.png");
                ImageIO.write(cropOne, "png", outputfileName);

                BufferedImage cropTwo = originalImage.getSubimage(450,250,550,100);
                File outputfileGeburt = new File("src\\main\\resources\\static\\images\\personalausweisVorneGeburtstag.png");
                ImageIO.write(cropTwo, "png", outputfileGeburt);


            } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

