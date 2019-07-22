package project.post_ident.classes;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Tess4J {

    public static Tesseract getTesseract() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng");
        return tesseract;
    }

    public static String getResult() {
        Tesseract tesseract = getTesseract();
        /*File file = new File("src\\main\\resources\\imageOutputs\\testTextOCR.png");*/
        /*File file = new File("src\\main\\resources\\imageOutputs\\personalausweisVorne.png");*/
        File file = new File("src\\main\\resources\\static\\images\\personalausweisVorne_clean.png");
        /*File file = new File("src\\main\\resources\\personalausweisVorne_clean.png");*/
        String result = null;
        try {

            result = tesseract.doOCR(file);

        } catch
        (
                TesseractException e) {
            e.printStackTrace();
        }

        return result;
    }

}
