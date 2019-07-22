package project.post_ident.classes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

public class ImageFilters {

    public static void imageFiltering(){

        float[] matrix = {
                0.111f, 0.111f, 0.111f,
                0.111f, 0.111f, 0.111f,
                0.111f, 0.111f, 0.111f,
        };

        BufferedImageOp op = new ConvolveOp( new Kernel(3, 3, matrix) );
        try {
            BufferedImage originalImage = ImageIO.read(new File("src\\main\\resources\\static\\images\\perso_felix.png"));
            BufferedImage destImage= ImageIO.read(new File("src\\main\\resources\\static\\images\\perso_felixBlur.png"));

            BufferedImage blurredImage = op.filter(originalImage,destImage);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
