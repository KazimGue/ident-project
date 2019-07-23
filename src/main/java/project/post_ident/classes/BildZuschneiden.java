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
            BufferedImage originalImage = ImageIO.read(new File("src\\main\\resources\\static\\images\\ausweisOlli.png"));
            //BufferedImage originalImage = ImageIO.read(new File("src\\main\\resources\\static\\images\\ausweisOlli.png"));

                //width and height of each piece
                int firstPartWidth = 298;
                int firstPartHeight = 175;

                int firstX = 440;
                int firstY = 75;

                int secondPartWidth = 490;
                int secondPartHeight = 170;

                int secondX =440;
                int secondY = 235;

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

