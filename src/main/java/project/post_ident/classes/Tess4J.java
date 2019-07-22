package project.post_ident.classes;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

import static project.post_ident.classes.BildZuschneiden.bildZuschneiden;

public class Tess4J {

    public static Tesseract getTesseract() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng");
        return tesseract;
    }

    public static OCRResultObject getResult() {
        Tesseract tesseract = getTesseract();

        bildZuschneiden();
        File file = new File("src\\main\\resources\\static\\images\\personalausweisVorneName.jpg");
        File file2 = new File("src\\main\\resources\\static\\images\\personalausweisVorneGeburtstag.jpg");
        String result = null;
        String result2 = null;
        try {

            result = tesseract.doOCR(file );
            result2 = tesseract.doOCR(file2);

        } catch
        (
                TesseractException e) {
            e.printStackTrace();
        }

        OCRResultObject ocrResultObject= new OCRResultObject(result,result2);
        return ocrResultObject;
    }

}
